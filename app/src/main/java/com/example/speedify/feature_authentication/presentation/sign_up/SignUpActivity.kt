package com.example.speedify.feature_authentication.presentation.sign_up

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.R
import com.example.speedify.databinding.ActivitySignUpBinding
import com.example.speedify.feature_authentication.presentation.sign_in.SignInActivity

class SignUpActivity : AppCompatActivity() {

    private var _binding: ActivitySignUpBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dynamicConfiguration()

        //
    }

    private fun dynamicConfiguration() {
        //      Back button
        val iconBack = binding.icBack
        iconBack.setOnClickListener {
            onBackPressed()
        }
        //    go to back to sign in page
        binding.fieldBtn.btnLogin.setOnClickListener {
            onBackPressed()
        }
    }
}