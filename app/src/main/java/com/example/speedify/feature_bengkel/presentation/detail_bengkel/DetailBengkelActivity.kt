package com.example.speedify.feature_bengkel.presentation.detail_bengkel

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.R
import com.example.speedify.core.utils.fromJson
import com.example.speedify.core.utils.setImageFromUrl
import com.example.speedify.databinding.ActivityDetailBengkelBinding
import com.example.speedify.feature_bengkel.presentation.checkout_bengkel.CheckoutBengkelActivity
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.adapter.BengkelServicesAdapter
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.view_model.DetailBengkelViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.Serializable

@AndroidEntryPoint
class DetailBengkelActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDetailBengkelBinding

    private val binding get() = _binding

    private val viewModel: DetailBengkelViewModel by viewModels()

    private val bengkelServicesAdapter by lazy {
        BengkelServicesAdapter()
    }

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

        observeDetailBengkel()
        initAdapter()
        setupTextChangeListeners()
        updateButtonState()
        sendUserData()

        val iconBack = binding.icBack
        iconBack.setOnClickListener {
            onBackPressed()
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

    private fun setupTextChangeListeners() {
        binding.titleDetailLocationEditText.addTextChangedListener { updateButtonState() }
        binding.titleFullNameEditText.addTextChangedListener { updateButtonState() }
        binding.titleDetailComplaintEditText.addTextChangedListener { updateButtonState() }
    }

    private fun updateButtonState() {
        val isLocationEmpty = binding.titleDetailLocationEditText.text.isNullOrEmpty()
        val isNameEmpty = binding.titleFullNameEditText.text.isNullOrEmpty()
        val isComplaintsEmpty = binding.titleDetailComplaintEditText.text.isNullOrEmpty()

        // Button should be enabled only if all fields are filled
        val isButtonEnabled = !isLocationEmpty && !isNameEmpty && !isComplaintsEmpty

        // Set button color
        val buttonColor = if (isButtonEnabled) {
            getColor(R.color.cinnabar)
        } else {
            getColor(R.color.roman_silver)
        }
        binding.btnPlaceOrderDetailBengkel.setBackgroundColor(buttonColor)
        binding.btnPlaceOrderDetailBengkel.setTextColor(Color.WHITE)
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

    private fun sendUserData() {
        val placeOrder = binding.btnPlaceOrderDetailBengkel

        placeOrder.setOnClickListener {
            val detailLocation = binding.titleDetailLocationEditText.text.toString()
            val userName = binding.titleFullNameEditText.text.toString()
            val detailComplaints = binding.titleDetailComplaintEditText.text.toString()
            val selectedServices = bengkelServicesAdapter.getSelectedServices()


            var isEmptyField = false
            if (detailLocation.isEmpty()) {
                binding.titleDetailLocationEditText.error = "Field tidak boleh kosong"
                isEmptyField = true
            }

            if (userName.isEmpty()) {
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
                val intent = Intent(this, CheckoutBengkelActivity::class.java).apply {
                    putExtra(
                        CheckoutBengkelActivity.DETAIL_LOCATION,
                        detailLocation
                    )
                    putExtra(CheckoutBengkelActivity.USER_NAME, userName)
                    putExtra(
                        CheckoutBengkelActivity.DETAIL_COMPLAINT,
                        detailComplaints
                    )
                    putExtra(
                        CheckoutBengkelActivity.SELECTED_SERVICES,
                        ArrayList(selectedServices) as Serializable
                    )
                }

                startActivity(intent)
            }
        }
    }

    private fun initAdapter() {
        binding.rvItemServices.apply {
            adapter = bengkelServicesAdapter
            layoutManager =
                LinearLayoutManager(this@DetailBengkelActivity, LinearLayoutManager.VERTICAL, false)
        }
    }


    companion object {
        const val EXTRA_BENGKEL_ID = "BENGKEL_ID"
    }
}