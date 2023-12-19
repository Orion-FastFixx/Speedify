package com.example.speedify.feature_bengkel.presentation.detail_bengkel

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.R
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.core.utils.OrderTimeManager
import com.example.speedify.core.utils.fromJson
import com.example.speedify.core.utils.isInternetAvailable
import com.example.speedify.core.utils.setImageFromUrl
import com.example.speedify.databinding.ActivityDetailBengkelBinding
import com.example.speedify.feature_bengkel.presentation.checkout_bengkel.CheckoutBengkelActivity
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.adapter.BengkelServicesAdapter
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.view_model.DetailBengkelViewModel
import com.example.speedify.feature_bengkel.presentation.review_bengkel.BengkelReviewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailBengkelActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDetailBengkelBinding

    private val binding get() = _binding

    private val viewModel: DetailBengkelViewModel by viewModels()

    private val bengkelServicesAdapter by lazy {
        BengkelServicesAdapter()
    }

    @Inject
    lateinit var userDataStore: UserDataStoreImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBengkelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bengkelId = intent.getIntExtra(EXTRA_BENGKEL_ID, 0)

        if (bengkelId != 0) {
            viewModel.getDetailBengkel(bengkelId)
            viewModel.getLayananBengkel(bengkelId)
        } else {
            Log.e(ContentValues.TAG, "Bengkel ID is not provided")
        }

        setDefaultUserName()
        observeDetailBengkel()
        initAdapter()
        setupTextChangeListeners()
        updateButtonState()
        setAction()
        observeOrderServiceResult()

        val iconBack = binding.icBack
        iconBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initAdapter() {
        binding.rvItemServices.apply {
            adapter = bengkelServicesAdapter
            layoutManager =
                LinearLayoutManager(this@DetailBengkelActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupTextChangeListeners() {
        binding.titleDetailLocationEditText.addTextChangedListener { updateButtonState() }
        binding.titleFullNameEditText.addTextChangedListener { updateButtonState() }
        binding.titleDetailComplaintEditText.addTextChangedListener { updateButtonState() }
    }

    fun updateButtonState() {
        val isLocationEmpty = binding.titleDetailLocationEditText.text.isNullOrEmpty()
        val isNameEmpty = binding.titleFullNameEditText.text.isNullOrEmpty()
        val isComplaintsEmpty = binding.titleDetailComplaintEditText.text.isNullOrEmpty()
        val isServiceEmpty = bengkelServicesAdapter.getSelectedServices().isEmpty()

        // Button should be enabled only if all fields are filled
        val isButtonEnabled =
            !isLocationEmpty && !isNameEmpty && !isComplaintsEmpty && !isServiceEmpty

        binding.btnPlaceOrderDetailBengkel.apply {
            isEnabled = isButtonEnabled // Enable or disable the button
            setBackgroundResource(if (isButtonEnabled) R.drawable.btn_background else R.drawable.btn_background_inactive)
        }
        binding.tvPlaceOrderDetailBengkel.setTextColor(Color.WHITE)
        binding.tvPlaceOrderDetailBengkel.visibility = View.VISIBLE
    }

    private fun updateServiceError() {
        if (bengkelServicesAdapter.getSelectedServices().isNotEmpty()) {
            binding.tvError.text = "" // Clear the error message
            binding.tvError.visibility = View.GONE // Hide the error text view
        } else {
            binding.tvError.text = "Pilihlah setidaknya satu"
            binding.tvError.visibility = View.VISIBLE // Show the error text view
        }
    }

    private fun setAction() {
        val bengkelId = intent.getIntExtra(EXTRA_BENGKEL_ID, 0)

        binding.btnPlaceOrderDetailBengkel.setOnClickListener {
            if (OrderTimeManager.isTimerRunning && OrderTimeManager.currentBengkelId == bengkelId) {
                redirectToCheckout(bengkelId)
            } else {
                if (binding.btnPlaceOrderDetailBengkel.isEnabled) {
                    setLoadingState(true)
                    OrderTimeManager.resetTimer(bengkelId)
                    orderBengkelService()
                }
            }
        }

        binding.tvRatingReviewDetailBengkel.setOnClickListener {
            lifecycleScope.launch {
                viewModel.detailBengkelState.collect { state ->
                    val intent = Intent(this@DetailBengkelActivity, BengkelReviewActivity::class.java)
                    intent.putExtra(
                        BengkelReviewActivity.EXTRA_DETAIL_BENGKEL,
                        state.detailBengkel
                    )
                    startActivity(intent)
                }
            }
        }
    }

    private fun setDefaultUserName() {
        lifecycleScope.launch {
            val userPreferences = userDataStore.getUser()
            val userName = userPreferences.name ?: ""
            binding.titleFullNameEditText.setText(userName)
        }
    }

    private fun orderBengkelService() {
        val detailLocation = binding.titleDetailLocationEditText.text.toString().trim()
        val fullName = binding.titleFullNameEditText.text.toString().trim()
        val detailComplaints = binding.titleDetailComplaintEditText.text.toString().trim()
        val selectedServices = bengkelServicesAdapter.getSelectedServices()

        // Check network availability first
        if (!isInternetAvailable(this)) {
            Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
                .show()
            return
        }

        var isEmptyField = false
        if (detailLocation.isEmpty()) {
            binding.titleDetailLocationEditText.error = "Field tidak boleh kosong"
            isEmptyField = true
        }

        if (fullName.isEmpty()) {
            binding.titleFullNameEditText.error = "Field tidak boleh kosong"
            isEmptyField = true
        }

        if (detailComplaints.isEmpty()) {
            binding.titleDetailComplaintEditText.error = "Field tidak boleh kosong"
            isEmptyField = true
        }

        if (selectedServices.isEmpty()) {
            binding.tvError.text = "Pilihlah setidaknya satu"
            binding.tvError.visibility = View.VISIBLE
            isEmptyField = true
        } else {
            updateServiceError()
        }

        if (!isEmptyField) {
            val serviceIds = selectedServices.map { it.id }
            val bengkelId = intent.getIntExtra(EXTRA_BENGKEL_ID, 0)

            viewModel.orderBengkelService(
                bengkelId = bengkelId,
                serviceId = serviceIds,
                additionalInfo = detailLocation,
                fullName = fullName,
                complaint = detailComplaints
            )
        }
    }

    private fun observeDetailBengkel() {
        lifecycleScope.launch {
            try {
                viewModel.detailBengkelState.collect { state ->
                    if (state.isLoading) {
                        Log.d(ContentValues.TAG, "DetailBengkel:   Loading")
                    } else if (state.error != null) {
                        Log.e(ContentValues.TAG, "DetailBengkel:   ${state.error}")
                    } else {
                        state.layanan?.let {
                            bengkelServicesAdapter.setItems(it)
                        }

                        binding.apply {
                            tvTitleToolbar.text = state.detailBengkel?.namaBengkel

                            // image
                            val imageUrls = state.detailBengkel?.fotoUrl ?: "[]"
                            val image: List<String> = fromJson(imageUrls)
                            if (image.isNotEmpty()) {
                                ivDetailBengkel.setImageFromUrl(
                                    this@DetailBengkelActivity,
                                    image[0]
                                )
                            }
                            //     end image

                            tvTitleDetailBengkel.text = state.detailBengkel?.namaBengkel
                            tvDescriptionDetailBengkel.text = state.detailBengkel?.deskripsi
                            tvRatingDetailBengkel.text =
                                state.detailBengkel?.rating?.firstOrNull()?.averageRating?.toString()
                                    ?: "0"
                            tvReviewDetailBengkel.text = String.format(
                                "(%s reviews)",
                                state.detailBengkel?.rating?.firstOrNull()?.reviewCount?.toString()
                                    ?: "0"
                            )
                        }
                    }

                }

            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "observeDetailBengkel: ${e.message}")
            }
        }
    }

    private fun observeOrderServiceResult() {
        lifecycleScope.launch {
            try {
                viewModel.detailBengkelState.collect { state ->
                    if (state.isLoading) {
                        // Show loading indicator
                        // setLoadingState(true)
                    } else if (state.error != null) {
                        // Handle error
                        setLoadingState(false)
                        Toast.makeText(this@DetailBengkelActivity, state.error, Toast.LENGTH_LONG)
                            .show()
                    } else if (state.orderBengkelService != null) {
                        // Order service successful
                        setLoadingState(false)
                        val intent =
                            Intent(this@DetailBengkelActivity, CheckoutBengkelActivity::class.java)
                        intent.putExtra(
                            CheckoutBengkelActivity.EXTRA_ORDER_DATA,
                            state.orderBengkelService
                        )
                        intent.putExtra(
                            CheckoutBengkelActivity.EXTRA_DETAIL_BENGKEL,
                            state.detailBengkel
                        )
                        startActivity(intent)
                    }
                }
            } catch (e: Exception) {
                // Handle the exception
                Log.e(ContentValues.TAG, "observeOrderServiceResult: ${e.message}")
            }
        }
    }

    private fun redirectToCheckout(bengkelId: Int) {
        if (OrderTimeManager.currentBengkelId == bengkelId) {
            lifecycleScope.launch {
                viewModel.detailBengkelState.collect { state ->
                    val intent =
                        Intent(this@DetailBengkelActivity, CheckoutBengkelActivity::class.java)
                    intent.putExtra(
                        CheckoutBengkelActivity.EXTRA_ORDER_DATA,
                        state.orderBengkelService
                    )
                    intent.putExtra(
                        CheckoutBengkelActivity.EXTRA_DETAIL_BENGKEL,
                        state.detailBengkel
                    )
                    startActivity(intent)
                }
            }
        }

    }

    private fun setLoadingState(isLoading: Boolean) {
        binding.apply {
            titleDetailLocationEditText.isEnabled = !isLoading
            titleFullNameEditText.isEnabled = !isLoading
            titleDetailComplaintEditText.isEnabled = !isLoading

            btnPlaceOrderDetailBengkel.isEnabled = !isLoading
            tvPlaceOrderDetailBengkel.visibility = if (isLoading) View.GONE else View.VISIBLE
            progressPlaceOrderDetailBengkel.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }


    companion object {
        const val EXTRA_BENGKEL_ID = "BENGKEL_ID"
    }
}