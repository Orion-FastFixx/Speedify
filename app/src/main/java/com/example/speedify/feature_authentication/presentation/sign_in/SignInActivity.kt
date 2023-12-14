package com.example.speedify.feature_authentication.presentation.sign_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.speedify.R
import com.example.speedify.databinding.ActivitySignInBinding
import com.example.speedify.feature_authentication.presentation.sign_up.SignUpActivity

class SignInActivity : AppCompatActivity() {

    private var _binding: ActivitySignInBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dynamicConfiguration()
    }

    private fun dynamicConfiguration() {
    //     go to register page
        binding.fieldBtn.btnRegis.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}