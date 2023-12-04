package com.example.speedify.feature_payment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.R
import com.example.speedify.databinding.ActivityPaymentMethodsBinding
import com.example.speedify.feature_bengkel.presentation.checkout_bengkel.CheckoutBengkelActivity

class PaymentMethodsActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityPaymentMethodsBinding

    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPaymentMethodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val balance = binding.paymentMethodsBengkel
        balance.tvOvoBalance.visibility = View.GONE
        balance.tvGopayBalance.visibility = View.GONE

        val currentPaymentMethodName = intent.getStringExtra(CURRENT_PAYMENT_METHOD_NAME)
        setInitialPaymentMethodState(currentPaymentMethodName)

        dynamicConfiguration()

        // Back button
        val iconBack = binding.paymentMethodsBengkel.icBack
        iconBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setInitialPaymentMethodState(currentPaymentMethod: String?) {
        when (currentPaymentMethod) {
            "Cash" -> selectPaymentMethod(PaymentMethod.CASH)
            "OVO" -> selectPaymentMethod(PaymentMethod.OVO)
            "GoPay" -> selectPaymentMethod(PaymentMethod.GOPAY)
            else -> { /* No payment method selected */
            }
        }
    }

    private fun dynamicConfiguration() {
        val cash = binding.paymentMethodsBengkel.llCash
        cash.setOnClickListener {
            selectPaymentMethod(PaymentMethod.CASH)
        }
        val ovo = binding.paymentMethodsBengkel.llOvo
        ovo.setOnClickListener {
            selectPaymentMethod(PaymentMethod.OVO)
        }
        val gopay = binding.paymentMethodsBengkel.llGopay
        gopay.setOnClickListener {
            selectPaymentMethod(PaymentMethod.GOPAY)
        }
    }


    private fun selectPaymentMethod(paymentMethod: PaymentMethod) {
//        Conditional payment checked
        val radioBtn = binding.paymentMethodsBengkel
        radioBtn.ivRadioCash.setImageResource(R.drawable.ic_radio_unchecked)
        radioBtn.ivRadioOvo.setImageResource(R.drawable.ic_radio_unchecked)
        radioBtn.ivRadioGopay.setImageResource(R.drawable.ic_radio_unchecked)

        val balance = binding.paymentMethodsBengkel

        val btnSave = binding.paymentMethodsBengkel.btnSavePaymentMethod

        when (paymentMethod) {
            PaymentMethod.CASH -> {
                radioBtn.ivRadioCash.setImageResource(R.drawable.ic_radio_check_fill)
                balance.tvOvoBalance.visibility = View.GONE
                balance.tvGopayBalance.visibility = View.GONE

                btnSave.setOnClickListener {
                    val intent = Intent().apply {
                        putExtra(CheckoutBengkelActivity.PAYMENT_METHOD_NAME, "Cash")
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }

            PaymentMethod.OVO -> {
                radioBtn.ivRadioOvo.setImageResource(R.drawable.ic_radio_check_fill)
                balance.tvOvoBalance.visibility = View.VISIBLE
                balance.tvOvoBalance.text = getString(R.string.dummy_nominal)
                balance.tvGopayBalance.visibility = View.GONE

                btnSave.setOnClickListener {
                    val intent = Intent().apply {
                        putExtra(CheckoutBengkelActivity.PAYMENT_METHOD_NAME, "OVO")
                        putExtra(CheckoutBengkelActivity.BALANCE, balance.tvOvoBalance.text)
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }

            PaymentMethod.GOPAY -> {
                radioBtn.ivRadioGopay.setImageResource(R.drawable.ic_radio_check_fill)
                balance.tvGopayBalance.visibility = View.VISIBLE
                balance.tvGopayBalance.text = getString(R.string.dummy_nominal)
                balance.tvOvoBalance.visibility = View.GONE

                btnSave.setOnClickListener {
                    val intent = Intent().apply {
                        putExtra(CheckoutBengkelActivity.PAYMENT_METHOD_NAME, "GoPay")
                        putExtra(CheckoutBengkelActivity.BALANCE, balance.tvGopayBalance.text)
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    enum class PaymentMethod {
        CASH, OVO, GOPAY
    }

    companion object {
        const val CURRENT_PAYMENT_METHOD_NAME = "key_current_payment_method_name"
    }
}