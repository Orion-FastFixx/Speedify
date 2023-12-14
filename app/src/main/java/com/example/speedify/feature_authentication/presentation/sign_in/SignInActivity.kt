package com.example.speedify.feature_authentication.presentation.sign_in

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        super.onBackPressedDispatcher.onBackPressed()
        finishAffinity()
    }

    private fun dynamicConfiguration() {
        //     hide username field
        binding.fieldAuth.tvUsername.visibility = View.GONE
        binding.fieldAuth.authUsernameTextLayout.visibility = View.GONE

        //     go to register page
        binding.fieldBtn.btnRegis.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}