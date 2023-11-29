package com.example.speedify.feature_bengkel.presentation.checkout_bengkel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.databinding.ActivityCheckoutBengkelBinding

class CheckoutBengkelActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityCheckoutBengkelBinding

    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCheckoutBengkelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val morePayment = binding.icArrowRightPaymentMethod
        morePayment.setOnClickListener {
            val intent = Intent(this, PaymentMethodsActivity::class.java)
            startActivity(intent)
        }
    }
}