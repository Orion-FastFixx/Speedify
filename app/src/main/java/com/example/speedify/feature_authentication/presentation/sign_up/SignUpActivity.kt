package com.example.speedify.feature_authentication.presentation.sign_up

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.speedify.R
import com.example.speedify.core.utils.animateVisibility
import com.example.speedify.core.utils.isInternetAvailable
import com.example.speedify.databinding.ActivitySignUpBinding
import com.example.speedify.feature_authentication.presentation.sign_in.SignInActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private var _binding: ActivitySignUpBinding? = null

    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setAction()

        binding.fieldAuth.authEmailEditText.addTextChangedListener(emailWatcher)
        binding.fieldAuth.authPasswordEditText.addTextChangedListener(passwordWatcher)
        observeRegisterResult()
    }

    private fun setAction() {
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

        val emailTextLayout = binding.fieldAuth.authEmailTextLayout
        val passwordTextLayout = binding.fieldAuth.authPasswordTextLayout

        // Check network availability first
        if (!isInternetAvailable(this)) {
            Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
                .show()
            return
        }

        // Check if the fields are not empty
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, getString(R.string.fill_in_all_fields), Toast.LENGTH_SHORT).show()
            return
        }

        // Check if the email is valid
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextLayout.error = getString(R.string.invalid_email)
            return
        } else {
            emailTextLayout.error = null
        }

        // Check if the password is at least 8 characters
        if (password.length < 8) {
            passwordTextLayout.error = getString(R.string.invalid_password)
            return
        } else {
            passwordTextLayout.error = null
        }
        // Call the signUp function in the ViewModel
        viewModel.signUp(email, password, username)
    }

    private fun observeRegisterResult() {
        // Observe the signUpState using repeatOnLifecycle
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                try {
                    viewModel.signUpState.collect { state ->
                        if (state.isLoading) {
                            // Show loading indicator
                            setLoadingState(true)
                        } else {
                            setLoadingState(false)
                            if (state.error != null) {
                                // If there is an error, show it
                                Snackbar.make(
                                    binding.root,
                                    "Sorry, there was a problem with your request: ${state.error}",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            } else if (state.user != null) {
                                // If sign-up is successful, show a success message and redirect to sign-in
                                Toast.makeText(
                                    this@SignUpActivity,
                                    "Sign up successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                                Intent(this@SignUpActivity, SignInActivity::class.java).also {
                                    startActivity(it)
                                    finish()
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    // Handle the exception
                    Snackbar.make(
                        binding.root,
                        "An unexpected error occurred: ${e.localizedMessage}",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setLoadingState(isLoading: Boolean) {
        binding.apply {
            fieldAuth.authUsernameEditText.isEnabled = !isLoading
            fieldAuth.authEmailEditText.isEnabled = !isLoading
            fieldAuth.authPasswordEditText.isEnabled = !isLoading

            if (isLoading) {
                viewLoading.animateVisibility(true)
            } else {
                viewLoading.animateVisibility(false)
            }
        }
    }

    private val passwordWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Not used, but must be implemented
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Not used, but must be implemented
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null && s.isNotEmpty()) {
                binding.fieldAuth.authPasswordTextLayout.error = null
            }
        }
    }

    private val emailWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Not used, but must be implemented
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Not used, but must be implemented
        }

        override fun afterTextChanged(s: Editable?) {
            if (s != null && s.isNotEmpty()) {
                binding.fieldAuth.authEmailTextLayout.error = null
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}