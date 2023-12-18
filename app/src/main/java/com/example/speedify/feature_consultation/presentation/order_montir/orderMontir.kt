package com.example.speedify.feature_consultation.presentation.order_montir

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.R
import com.example.speedify.databinding.ActivityOrderMontirBinding
import com.example.speedify.feature_bengkel.presentation.checkout_bengkel.CheckoutBengkelActivity
import com.example.speedify.feature_payment.PaymentMethodsActivity

class OrderMontir : AppCompatActivity() {

    private lateinit var _binding: ActivityOrderMontirBinding

    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOrderMontirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dynamicConfiguration()

        // Back button
        val iconBack = binding.orderMontir.icBack
        iconBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun dynamicConfiguration() {
        // set appbar title
        val title = binding.orderMontir.tvTitleToolbar
            title.text = "Bengkel Umum"
        // end set appbar title

        //     set mitra info
        val mitraImg = binding.orderMontir.civCheckout
        val mitraTitle = binding.orderMontir.tvTitleCheckout
        val mitraName = binding.orderMontir.tvSubtitleCheckout

        mitraImg.setImageResource(R.drawable.montir2)
        mitraTitle.text = "Bengkel Umum"
        mitraName.text = "Reza Rizki"
        // end set mitra info

        //     hide input field
        binding.orderMontir.llContainerUserFieldCheckout.visibility = View.GONE
        // end hide input field

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

        //     remove btn pay component
        binding.orderMontir.btnPayCheckout.visibility = View.GONE
        //     end remove btn pay component
    }

    //   Get data from PaymentMethodsActivity
    private val paymentMethodResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intent ->
                    val paymentMethod =
                        intent.getStringExtra(CheckoutBengkelActivity.PAYMENT_METHOD_NAME)
                    val balance = intent.getStringExtra(CheckoutBengkelActivity.BALANCE)
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
}
