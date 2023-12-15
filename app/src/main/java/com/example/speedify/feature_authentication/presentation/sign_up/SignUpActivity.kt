package com.example.speedify.feature_authentication.presentation.sign_up

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.databinding.ActivitySignUpBinding
import com.example.speedify.feature_authentication.presentation.sign_in.SignInActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private var _binding: ActivitySignUpBinding? = null

    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dynamicConfiguration()

        val state = viewModel.signUpState.value

        if (state.isLoading) {
            Log.d(TAG, "Loading")
            // You can show a loading indicator if needed
        } else if (state.error != null) {
            Snackbar.make(
                binding.root,
                "Sorry, there was a problem with your request ",
                Snackbar.LENGTH_SHORT
            ).show()
            // Handle error, show error message, etc.
        } else {
            state.user?.let {
                Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()

                Intent(this, SignInActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
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

        // Call signUp function when the sign-up button is clicked
        binding.fieldBtn.btnRegis.setOnClickListener {
            signUpAction()
        }
    }

    private fun signUpAction() {
        val username = binding.fieldAuth.authUsernameEditText.text.toString().trim()
        val email = binding.fieldAuth.authEmailEditText.text.toString().trim()
        val password = binding.fieldAuth.authPasswordEditText.text.toString().trim()

        // Check if the fields are not empty
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if the email is valid
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if the password is at least 8 characters
        if (password.length < 8) {
            Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // Call the signUp function in the ViewModel
        viewModel.signUp(email, password, username)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}