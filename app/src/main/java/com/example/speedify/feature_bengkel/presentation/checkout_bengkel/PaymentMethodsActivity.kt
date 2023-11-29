package com.example.speedify.feature_bengkel.presentation.checkout_bengkel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.R
import com.example.speedify.databinding.ActivityPaymentMethodsBinding

class PaymentMethodsActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityPaymentMethodsBinding

    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPaymentMethodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val balance = binding.paymentMethodsBengkel
        balance.tvOvoBalance.text = ""
        balance.tvGopayBalance.text = ""

        dynamicConfiguration()
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


    fun selectPaymentMethod(paymentMethod: PaymentMethod) {
//        Conditional payment checked
        val radioBtn = binding.paymentMethodsBengkel
        radioBtn.ivRadioCash.setImageResource(R.drawable.ic_radio_unchecked)
        radioBtn.ivRadioOvo.setImageResource(R.drawable.ic_radio_unchecked)
        radioBtn.ivRadioGopay.setImageResource(R.drawable.ic_radio_unchecked)

        val balance = binding.paymentMethodsBengkel

        when (paymentMethod) {
            PaymentMethod.CASH -> {
                radioBtn.ivRadioCash.setImageResource(R.drawable.ic_radio_check_fill)
                balance.tvOvoBalance.text = ""
                balance.tvGopayBalance.text = ""
            }

            PaymentMethod.OVO -> {
                radioBtn.ivRadioOvo.setImageResource(R.drawable.ic_radio_check_fill)
                balance.tvOvoBalance.text = getString(R.string.dummy_nominal)
                balance.tvGopayBalance.text = ""
            }

            PaymentMethod.GOPAY -> {
                radioBtn.ivRadioGopay.setImageResource(R.drawable.ic_radio_check_fill)
                balance.tvGopayBalance.text = getString(R.string.dummy_nominal)
                balance.tvOvoBalance.text = ""
            }
        }
    }

    enum class PaymentMethod {
        CASH, OVO, GOPAY
    }
}