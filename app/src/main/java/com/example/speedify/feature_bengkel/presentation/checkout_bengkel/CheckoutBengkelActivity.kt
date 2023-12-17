package com.example.speedify.feature_bengkel.presentation.checkout_bengkel

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.speedify.MainActivity
import com.example.speedify.R
import com.example.speedify.core.utils.currencyFormat
import com.example.speedify.core.utils.currencyFormatWithoutRp
import com.example.speedify.core.utils.toCamelCase
import com.example.speedify.databinding.ActivityCheckoutBengkelBinding
import com.example.speedify.feature_bengkel.data.model.OrderBengkelServiceResponse
import com.example.speedify.feature_bengkel.data.model.ServicesItemCheckout
import com.example.speedify.feature_payment.PaymentMethodsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutBengkelActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityCheckoutBengkelBinding

    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCheckoutBengkelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dynamicConfiguration()
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

//      Get user data
//         getUserData()
//      End get user data

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
        payService()
//      End button pay

//      Back button
        val iconBack = binding.checkoutBengkel.icBack
        iconBack.setOnClickListener {
            onBackPressed()
        }
//      End back button
    }

    private fun displayOrderDetails(orderData: OrderBengkelServiceResponse) {
        val detailLocation = binding.checkoutBengkel.titleDetailLocationEditText
        val userName = binding.checkoutBengkel.titleFullNameEditText
        val detailComplaints = binding.checkoutBengkel.titleDetailComplaintEditText

//        get field data from DetailBengkelActivity
        val location = orderData.data.additionalInfo.preciseLocation
        val name = orderData.data.additionalInfo.fullName
        val complaints = orderData.data.additionalInfo.complaint

        //      set field data to CheckoutBengkelActivity
        detailLocation.setText(location)
        userName.setText(name)
        detailComplaints.setText(complaints)

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
            }

            "OVO" -> {
                paymentMethodIcon.setImageResource(R.drawable.ic_ovo)
                paymentMethodView.text = "OVO"
                balanceView.visibility = View.VISIBLE
                balanceView.text = balance
            }

            "GoPay" -> {
                paymentMethodIcon.setImageResource(R.drawable.ic_gopay)
                paymentMethodView.text = "GoPay"
                balanceView.visibility = View.VISIBLE
                balanceView.text = balance
            }
        }
    }

    private fun payService() {
        val payService = binding.checkoutBengkel.btnPayCheckout

        payService.setOnClickListener {
            val detailLocation = binding.checkoutBengkel.titleDetailLocationEditText.text.toString()
            val userName = binding.checkoutBengkel.titleFullNameEditText.text.toString()
            val detailComplaints =
                binding.checkoutBengkel.titleDetailComplaintEditText.text.toString()

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
        }
    }

    private fun orderTimeOut() {
        val timeout = binding.checkoutBengkel.tvTimeout
        val fiveMinutesInMillis = 1 * 60 * 1000L // 5 minutes in milliseconds

        //     timer for 5 minutes
        val timer = object : CountDownTimer(fiveMinutesInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = millisUntilFinished % 60000 / 1000
                timeout.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                timeout.text = getString(R.string.timeout)

                val intent = Intent(this@CheckoutBengkelActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
        timer.start()
    }


    companion object {
        const val PAYMENT_METHOD_NAME = "key_payment_method_name"
        const val BALANCE = "key_balance"
        const val EXTRA_ORDER_DATA = "EXTRA_ORDER_DATA"
    }
}