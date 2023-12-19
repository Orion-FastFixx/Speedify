package com.example.speedify.feature_consultation.presentation.order_montir

import android.app.Activity
import android.content.ContentValues
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
import androidx.lifecycle.lifecycleScope
import com.example.speedify.MainActivity
import com.example.speedify.R
import com.example.speedify.core.utils.OrderTimeManager
import com.example.speedify.core.utils.toCamelCase
import com.example.speedify.databinding.ActivityOrderMontirBinding
import com.example.speedify.feature_consultation.data.model.OrderMontirServiceResponse
import com.example.speedify.feature_consultation.data.model.ServicesItemMontir
import com.example.speedify.feature_payment.PaymentMethodsActivity
import com.example.speedify.core.utils.currencyFormat
import com.example.speedify.core.utils.currencyFormatWithoutRp
import com.example.speedify.core.utils.fromJson
import com.example.speedify.core.utils.isInternetAvailable
import com.example.speedify.core.utils.setImageFromUrl
import com.example.speedify.feature_bengkel.data.model.OrderBengkelServiceResponse
import com.example.speedify.feature_bengkel.presentation.checkout_bengkel.CheckoutBengkelActivity
import com.example.speedify.feature_consultation.data.model.DaftarItem
import kotlinx.coroutines.launch


class OrderMontirActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityOrderMontirBinding

    private val binding get() = _binding

    private val viewModel: OrderMontirViewModel by viewModels()

    private var paymentMethodId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOrderMontirBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dynamicConfiguration()
        setAction()
        observePayServiceResult()
        orderTimeOut()

        val orderData = intent.getParcelableExtra<OrderMontirServiceResponse>(
            OrderMontirActivity.EXTRA_ORDER_DATA
        )
        if (orderData != null) {
            displayOrderDetails(orderData)
            displaySelectedServicesAndPrice(orderData.data.services, orderData)
        }
    }

    private fun dynamicConfiguration() {
//      Set text order summary
        val orderSummary = binding.orderMontir.orderSummary.tvSectionName
        orderSummary.text = getString(R.string.order_summary)
//      End set text order summary

//        Set text payment method
        val paymentMethod = binding.orderMontir.tvPaymentMethod
        paymentMethod.text = getString(R.string.payment_method)
//        End set text payment method

//      Go to Payment Methods
        val morePayment = binding.orderMontir.icArrowRightPaymentMethod
        morePayment.setOnClickListener {
            val currentPayment = binding.orderMontir.tvNominalPaymentMethod.text.toString()
            val intent = Intent(this, PaymentMethodsActivity::class.java).apply {
                putExtra(PaymentMethodsActivity.CURRENT_PAYMENT_METHOD_NAME, currentPayment)
            }
            paymentMethodResultLauncher.launch(intent)
        }
//      End go to Payment Methods

//      initial payment method section
        binding.orderMontir.tvNominalBalance.visibility = View.GONE
//      End initial payment method section

//      Back button
        val iconBack = binding.orderMontir.icBack
        iconBack.setOnClickListener {
            onBackPressed()
        }
//      End back button
    }

    private fun displayOrderDetails(orderData: OrderMontirServiceResponse) {

        // Mendapatkan objek DaftarItem dari intent
        val detailMontir = intent.getSerializableExtra(EXTRA_DETAIL_MONTIR) as? DaftarItem

        if (detailMontir != null) {
            val titleToolbar = binding.orderMontir.tvTitleToolbar
            val montirImg = binding.orderMontir.civCheckout
            val titleMontir = binding.orderMontir.tvTitleCheckout
            val subtitleMontir = binding.orderMontir.tvSubtitleCheckout

            // Mendapatkan data dari objek DaftarItem
            val montirName = detailMontir.nama
            val jenisMontir = detailMontir.jenisMontir

            val imageUrls = detailMontir.fotoUrl ?: "[]"
            val imageMontir: List<String> = fromJson(imageUrls)

            // Mengisi data ke elemen antarmuka pengguna (UI)
            titleToolbar.text = montirName
            titleMontir.text = montirName
            subtitleMontir.text = jenisMontir
            if (imageMontir.isNotEmpty()) {
                montirImg.setImageFromUrl(this@OrderMontirActivity, imageMontir[0])
            }
        } else {
            // Handle jika objek DaftarItem null
            // Misalnya, tampilkan pesan kesalahan atau lakukan tindakan lainnya
        }
    }

    private fun displaySelectedServicesAndPrice(
        services: List<ServicesItemMontir>,
        orderData: OrderMontirServiceResponse
    ) {
//      LinearLayout for services in CheckoutBengkelActivity
        val servicesLayout = binding.orderMontir.llDynamicServices
        servicesLayout.removeAllViews()

        // init subtotal price view
        val subtotalPriceView = binding.orderMontir.tvSubtotalPriceService

        // Set admin fee
        val adminFeeNominalView = binding.orderMontir.tvNominalAdminFeeService
        val adminFee = 1000
        adminFeeNominalView.text = String.format("%s", adminFee)

        // init total price view
        val totalPriceView = binding.orderMontir.tvTotalPrice

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
                    val paymentMethod = intent.getStringExtra(OrderMontirActivity.PAYMENT_METHOD_NAME)
                    val balance = intent.getStringExtra(OrderMontirActivity.BALANCE)
                    updatePaymentMethod(paymentMethod, balance)
                }
            }
        }


    private fun updatePaymentMethod(paymentMethod: String?, balance: String?) {
        val paymentMethodView = binding.orderMontir.tvNominalPaymentMethod
        val paymentMethodIcon = binding.orderMontir.icPaymentMethod
        val balanceView = binding.orderMontir.tvNominalBalance

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
        binding.orderMontir.btnPayCheckout.setOnClickListener {
            payService()
        }
    }

    private fun payService() {

        // Check network availability first
        if (!isInternetAvailable(this)) {
            Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show()
            return
        }

        val orderData = intent.getParcelableExtra<OrderMontirServiceResponse>(
            OrderMontirActivity.EXTRA_ORDER_DATA
            )
            val orderId = orderData?.data?.id ?: 0
            viewModel.payOrderService(orderId, paymentMethodId)
    }

    private fun observePayServiceResult() {
        lifecycleScope.launch {
            try {
                viewModel.orderMontirState.collect { state ->
                    if (state.isLoading) {
                        Log.d(ContentValues.TAG, "observePayServiceResult:    isLoading")
                    } else if (state.error != null) {
                        Log.d(ContentValues.TAG, "observePayServiceResult:    error")
                    } else if (state.payOrderResponse != null) {
                        Toast.makeText(
                            this@OrderMontirActivity,
                            state.payOrderResponse.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@OrderMontirActivity, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        }
                        startActivity(intent)
                        finish()
                    }
                }

            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "observePayServiceResult: ${e.message} ")
            }
        }
    }

    private fun orderTimeOut() {
        val timeout = binding.orderMontir.tvTimeout

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

                val intent = Intent(this@OrderMontirActivity, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                startActivity(intent)
                finish()
            }
        }
        val orderData = intent.getParcelableExtra<OrderBengkelServiceResponse>(
            CheckoutBengkelActivity.EXTRA_ORDER_DATA
        )

        timer.start()
        OrderTimeManager.isTimerRunning = true
        OrderTimeManager.currentBengkelId = orderData?.data?.bengkelId ?: 0
    }

    companion object {
        const val PAYMENT_METHOD_NAME = "key_payment_method_name"
        const val BALANCE = "key_balance"
        const val EXTRA_ORDER_DATA = "EXTRA_ORDER_DATA"
        const val EXTRA_DETAIL_MONTIR = "EXTRA_DETAIL_MONTIR"
    }
}
