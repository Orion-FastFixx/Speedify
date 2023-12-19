package com.example.speedify.feature_payment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.MainActivity
import com.example.speedify.databinding.ActivitySuccessOrderPaymentBinding

class SuccessOrderPaymentActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySuccessOrderPaymentBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySuccessOrderPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSuccessOrder.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }
    }
}