package com.example.speedify.feature_bengkel.presentation.checkout_bengkel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.R
import com.example.speedify.databinding.ActivityCheckoutBengkelBinding

class CheckoutBengkelActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityCheckoutBengkelBinding

    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCheckoutBengkelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dynamicConfiguration()
    }

    private fun dynamicConfiguration() {
//      Set text order summary
        val orderSummary = binding.checkoutBengkel.orderSummary.tvSectionName
        orderSummary.text = getString(R.string.order_summary)

//      Set text location
        val location = binding.checkoutBengkel.location.tvSectionName
        location.text = getString(R.string.section_location)

//      Set text payment method
        val paymentMethod = binding.checkoutBengkel.paymentMethod.tvSectionName
        paymentMethod.text = getString(R.string.payment_method)

//      Go to Payment Methods
        val morePayment = binding.checkoutBengkel.icArrowRightPaymentMethod
        morePayment.setOnClickListener {
            val intent = Intent(this, PaymentMethodsActivity::class.java)
            startActivity(intent)
        }
    }
}