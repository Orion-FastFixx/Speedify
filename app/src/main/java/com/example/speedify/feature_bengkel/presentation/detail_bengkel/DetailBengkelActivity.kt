package com.example.speedify.feature_bengkel.presentation.detail_bengkel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.databinding.ActivityDetailBengkelBinding
import com.example.speedify.feature_bengkel.presentation.checkout_bengkel.CheckoutBengkelActivity

class DetailBengkelActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDetailBengkelBinding

    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBengkelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dynamicConfiguration()
    }

    private fun dynamicConfiguration() {
//      Go to Checkout Bengkel
        val placeOrder = binding.btnPlaceOrderDetailBengkel
        placeOrder.setOnClickListener {
            val intent = Intent(this, CheckoutBengkelActivity::class.java)
            startActivity(intent)
        }
    }
}