package com.example.speedify.feature_bengkel.presentation.checkout_bengkel

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.speedify.MainActivity
import com.example.speedify.R
import com.example.speedify.core.utils.OrderTimeManager
import com.example.speedify.core.utils.currencyFormat
import com.example.speedify.core.utils.currencyFormatWithoutRp
import com.example.speedify.core.utils.fromJson
import com.example.speedify.core.utils.isInternetAvailable
import com.example.speedify.core.utils.setImageFromUrl
import com.example.speedify.core.utils.toCamelCase
import com.example.speedify.databinding.ActivityCheckoutBengkelBinding
import com.example.speedify.feature_bengkel.data.model.DetailBengkel
import com.example.speedify.feature_bengkel.data.model.OrderBengkelServiceResponse
import com.example.speedify.feature_bengkel.data.model.ServicesItemCheckout
import com.example.speedify.feature_payment.PaymentMethodsActivity
import com.example.speedify.feature_payment.SuccessOrderPaymentActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CheckoutBengkelActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityCheckoutBengkelBinding

    private val binding get() = _binding

    private val viewModel: CheckoutBengkelViewModel by viewModels()

    private var paymentMethodId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCheckoutBengkelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dynamicConfiguration()
        setAction()
        observePayServiceResult()
        orderTimeOut()

        val orderData = intent.getParcelableExtra<OrderBengkelServiceResponse>(EXTRA_ORDER_DATA)
        if (orderData != null) {
            displayOrderDetails(orderData)
            displaySelectedServicesAndPrice(orderData.data.services, orderData)
        }
    }

    private fun dynamicConfiguration() {
//      Set text order summary
        val orderSummary = binding.checkoutBengkel.orderSummary.tvSectionName
        orderSummary.text = getString(R.string.order_summary)
//      End set text order summary

//        Set text payment method
        val paymentMethod = binding.checkoutBengkel.tvPaymentMethod
        paymentMethod.text = getString(R.string.payment_method)
//        End set text payment method

//      Go to Payment Methods
        val morePayment = binding.checkoutBengkel.icArrowRightPaymentMethod
        morePayment.setOnClickListener {
            val currentPayment = binding.checkoutBengkel.tvNominalPaymentMethod.text.toString()
            val intent = Intent(this, PaymentMethodsActivity::class.java).apply {
                putExtra(PaymentMethodsActivity.CURRENT_PAYMENT_METHOD_NAME, currentPayment)
            }
            paymentMethodResultLauncher.launch(intent)
        }
//      End go to Payment Methods

//      initial payment method section
        binding.checkoutBengkel.tvNominalBalance.visibility = View.GONE
//      End initial payment method section

//      Button pay
        setupTextChangeListeners()
        updateButtonState()
//      End button pay

//      Back button
        val iconBack = binding.checkoutBengkel.icBack
        iconBack.setOnClickListener {
            onBackPressed()
        }
//      End back button
    }

    private fun displayOrderDetails(orderData: OrderBengkelServiceResponse) {

        val detailBengkel = intent.getParcelableExtra<DetailBengkel>(EXTRA_DETAIL_BENGKEL)

        val titleToolbar = binding.checkoutBengkel.tvTitleToolbar
        val bengkelImg = binding.checkoutBengkel.civCheckout
        val titleBengkel = binding.checkoutBengkel.tvTitleCheckout
        val subtitleBengkel = binding.checkoutBengkel.tvSubtitleCheckout
        val detailLocation = binding.checkoutBengkel.titleDetailLocationEditText
        val userName = binding.checkoutBengkel.titleFullNameEditText
        val detailComplaints = binding.checkoutBengkel.titleDetailComplaintEditText

//        get field data from DetailBengkelActivity
        val location = orderData.data.additionalInfo.preciseLocation
        val name = orderData.data.additionalInfo.fullName
        val complaints = orderData.data.additionalInfo.complaint
        val bengkelName = detailBengkel?.namaBengkel
        val jenisBengkel = detailBengkel?.jenisBengkel

        val imageUrls = detailBengkel?.fotoUrl ?: "[]"
        val imageBengkel: List<String> = fromJson(imageUrls)


        //      set field data to CheckoutBengkelActivity
        detailLocation.setText(location)
        userName.setText(name)
        detailComplaints.setText(complaints)
        titleToolbar.text = bengkelName
        titleBengkel.text = bengkelName
        subtitleBengkel.text = jenisBengkel
        if (imageBengkel.isNotEmpty()) {
            bengkelImg.setImageFromUrl(this@CheckoutBengkelActivity, imageBengkel[0])
        }

    }

    private fun displaySelectedServicesAndPrice(
        services: List<ServicesItemCheckout>,
        orderData: OrderBengkelServiceResponse
    ) {
//      LinearLayout for services in CheckoutBengkelActivity
        val servicesLayout = binding.checkoutBengkel.llDynamicServices
        servicesLayout.removeAllViews()

        // init subtotal price view
        val subtotalPriceView = binding.checkoutBengkel.tvSubtotalPriceService

        // Set admin fee
        val adminFeeNominalView = binding.checkoutBengkel.tvNominalAdminFeeService
        val adminFee = 1000
        adminFeeNominalView.text = String.format("%s", adminFee)

        // init total price view
        val totalPriceView = binding.checkoutBengkel.tvTotalPrice

        val inflater = LayoutInflater.from(this)
        var subtotalPrice = 0
        for (service in services) {
            val serviceView =
                inflater.inflate(R.layout.item_services_component, servicesLayout, false)
            val serviceName = serviceView.findViewById<TextView>(R.id.tv_name_service)
            val servicePrice = serviceView.findViewById<TextView>(R.id.tv_price_service)

            serviceName.text = service.layanan.toCamelCase()

            val formattedServicePrice = service.orderServices.price.currencyFormatWithoutRp()
            servicePrice.text = formattedServicePrice

            subtotalPrice += service.orderServices.price

            servicesLayout.addView(serviceView)
        }
        // Set subtotal price
        val formattedSubtotalPrice = subtotalPrice.currencyFormat()
        subtotalPriceView.text = formattedSubtotalPrice

        // Set total price
        val totalPrice = orderData.data.totalHarga
        val formattedTotalPrice = totalPrice.currencyFormat()
        totalPriceView.text = formattedTotalPrice
    }

    //   Get data from PaymentMethodsActivity
    private val paymentMethodResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intent ->
                    val paymentMethod = intent.getStringExtra(PAYMENT_METHOD_NAME)
                    val balance = intent.getStringExtra(BALANCE)
                    updatePaymentMethod(paymentMethod, balance)
                }
            }
        }

    private fun setupTextChangeListeners() {
        binding.checkoutBengkel.titleDetailLocationEditText.addTextChangedListener { updateButtonState() }
        binding.checkoutBengkel.titleFullNameEditText.addTextChangedListener { updateButtonState() }
        binding.checkoutBengkel.titleDetailComplaintEditText.addTextChangedListener { updateButtonState() }
    }

    private fun updateButtonState() {
        val isLocationEmpty =
            binding.checkoutBengkel.titleDetailLocationEditText.text.isNullOrEmpty()
        val isNameEmpty = binding.checkoutBengkel.titleFullNameEditText.text.isNullOrEmpty()
        val isComplaintsEmpty =
            binding.checkoutBengkel.titleDetailComplaintEditText.text.isNullOrEmpty()

        // Button should be enabled only if all fields are filled
        val isButtonEnabled = !isLocationEmpty && !isNameEmpty && !isComplaintsEmpty

        // Set button color
        val buttonColor = if (isButtonEnabled) {
            getColor(R.color.cinnabar)
        } else {
            getColor(R.color.roman_silver)
        }
        binding.checkoutBengkel.btnPayCheckout.setBackgroundColor(buttonColor)
        binding.checkoutBengkel.btnPayCheckout.setTextColor(Color.WHITE)
    }


    private fun updatePaymentMethod(paymentMethod: String?, balance: String?) {
        val paymentMethodView = binding.checkoutBengkel.tvNominalPaymentMethod
        val paymentMethodIcon = binding.checkoutBengkel.icPaymentMethod
        val balanceView = binding.checkoutBengkel.tvNominalBalance

        when (paymentMethod) {
            "Cash" -> {
                paymentMethodIcon.setImageResource(R.drawable.ic_cash)
                paymentMethodView.text = "Cash"
                balanceView.visibility = View.GONE
                paymentMethodId = 1
            }

            "OVO" -> {
                paymentMethodIcon.setImageResource(R.drawable.ic_ovo)
                paymentMethodView.text = "OVO"
                balanceView.visibility = View.VISIBLE
                balanceView.text = balance
                paymentMethodId = 2
            }

            "GoPay" -> {
                paymentMethodIcon.setImageResource(R.drawable.ic_gopay)
                paymentMethodView.text = "GoPay"
                balanceView.visibility = View.VISIBLE
                balanceView.text = balance
                paymentMethodId = 2
            }
        }
    }

    private fun setAction() {
        binding.checkoutBengkel.btnPayCheckout.setOnClickListener {
            payService()
        }
    }

    private fun payService() {
        val detailLocation =
            binding.checkoutBengkel.titleDetailLocationEditText.text.toString().trim()
        val userName = binding.checkoutBengkel.titleFullNameEditText.text.toString().trim()
        val detailComplaints =
            binding.checkoutBengkel.titleDetailComplaintEditText.text.toString().trim()

        // Reset errors
        binding.checkoutBengkel.titleDetailLocationEditText.error = null
        binding.checkoutBengkel.titleFullNameEditText.error = null
        binding.checkoutBengkel.titleDetailComplaintEditText.error = null

        // Check network availability first
        if (!isInternetAvailable(this)) {
            Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
                .show()
            return
        }

        var isEmptyField = false
        if (detailLocation.isEmpty()) {
            binding.checkoutBengkel.titleDetailLocationEditText.error = "Field harus diisi"
            isEmptyField = true
        }

        if (userName.isEmpty()) {
            binding.checkoutBengkel.titleFullNameEditText.error = "Field harus diisi"
            isEmptyField = true
        }

        if (detailComplaints.isEmpty()) {
            binding.checkoutBengkel.titleDetailComplaintEditText.error = "Field harus diisi"
            isEmptyField = true
        }

        if (!isEmptyField) {
            val orderData = intent.getParcelableExtra<OrderBengkelServiceResponse>(EXTRA_ORDER_DATA)
            val orderId = orderData?.data?.id ?: 0
            viewModel.payOrderService(orderId, paymentMethodId)
        }
    }

    private fun observePayServiceResult() {
        lifecycleScope.launch {
            try {
                viewModel.checkoutBengkelState.collect { state ->
                    if (state.isLoading) {
                        Log.d(TAG, "observePayServiceResult:    isLoading")
                    } else if (state.error != null) {
                        Log.d(TAG, "observePayServiceResult:    error")
                    } else if (state.payOrderResponse != null) {
                        val intent = Intent(
                            this@CheckoutBengkelActivity,
                            SuccessOrderPaymentActivity::class.java
                        )
                        startActivity(intent)
                        finish()
                    }
                }

            } catch (e: Exception) {
                Log.e(TAG, "observePayServiceResult: ${e.message} ")
            }
        }
    }

    private fun orderTimeOut() {
        val timeout = binding.checkoutBengkel.tvTimeout

        val timeToCountDown: Long = if (OrderTimeManager.isTimerRunning) {
            OrderTimeManager.remainingTimeInMilliSeconds
        } else {
            OrderTimeManager.remainingTimeInMilliSeconds =
                (5 * 60 + 5) * 1000   // 5 minutes and 5 seconds
            OrderTimeManager.remainingTimeInMilliSeconds
        }

        //     timer for 5 minutes
        val timer = object : CountDownTimer(timeToCountDown, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                OrderTimeManager.remainingTimeInMilliSeconds = millisUntilFinished
                val minutes = millisUntilFinished / 60000
                val seconds = millisUntilFinished % 60000 / 1000
                timeout.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                timeout.text = getString(R.string.timeout)
                OrderTimeManager.isTimerRunning = false

                val intent = Intent(this@CheckoutBengkelActivity, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                startActivity(intent)
                finish()
            }
        }
        val orderData = intent.getParcelableExtra<OrderBengkelServiceResponse>(EXTRA_ORDER_DATA)

        timer.start()
        OrderTimeManager.isTimerRunning = true
        OrderTimeManager.currentBengkelId = orderData?.data?.bengkelId ?: 0
    }


    companion object {
        const val PAYMENT_METHOD_NAME = "key_payment_method_name"
        const val BALANCE = "key_balance"
        const val EXTRA_ORDER_DATA = "EXTRA_ORDER_DATA"
        const val EXTRA_DETAIL_BENGKEL = "EXTRA_DETAIL_BENGKEL"
    }
}