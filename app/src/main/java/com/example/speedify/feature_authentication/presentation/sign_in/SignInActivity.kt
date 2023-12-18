package com.example.speedify.feature_authentication.presentation.sign_in

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.speedify.MainActivity
import com.example.speedify.R
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.core.utils.isInternetAvailable
import com.example.speedify.databinding.ActivitySignInBinding
import com.example.speedify.feature_authentication.presentation.sign_up.SignUpActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

    private var _binding: ActivitySignInBinding? = null

    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by viewModels()

    @Inject
    lateinit var userDataStore: UserDataStoreImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAction()
        binding.fieldAuth.authEmailEditText.addTextChangedListener(emailWatcher)
        binding.fieldAuth.authPasswordEditText.addTextChangedListener(passwordWatcher)
        observeSignInResult()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        super.onBackPressedDispatcher.onBackPressed()
        finishAffinity()
    }

    private fun setAction() {
        //     hide username field
        binding.fieldAuth.tvUsername.visibility = View.GONE
        binding.fieldAuth.authUsernameTextLayout.visibility = View.GONE

        //     go to register page
        binding.fieldBtn.btnRegis.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        //     go to main page
        binding.fieldBtn.btnLogin.setOnClickListener {
            loginAction()
        }
    }

    private fun loginAction() {
        val email = binding.fieldAuth.authEmailEditText.text.toString()
        val password = binding.fieldAuth.authPasswordEditText.text.toString()

        val emailTextLayout = binding.fieldAuth.authEmailTextLayout
        val passwordTextLayout = binding.fieldAuth.authPasswordTextLayout

        // Check network availability first
        if (!isInternetAvailable(this)) {
            Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
                .show()
            return
        }

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, getString(R.string.fill_in_all_fields), Toast.LENGTH_SHORT).show()
            return
        }

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

        viewModel.signIn(email, password)
    }

    private fun observeSignInResult() {
        lifecycleScope.launch {
            try {
                viewModel.signInState.collect { state ->
                    if (state.isLoading) {
                        // Show loading indicator
                        setLoadingState(true)
                    } else {
                        setLoadingState(false)
                        if (state.error != null) {
                            Toast.makeText(
                                this@SignInActivity,
                                getString(R.string.check_credentials),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (state.user != null) {
                            userDataStore.saveUserToken(state.user.user.token)

                            // If the user is not null, go to the main activity
                            val intent = Intent(this@SignInActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
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

    private fun setLoadingState(isLoading: Boolean) {
        binding.apply {
            fieldAuth.authEmailEditText.isEnabled = !isLoading
            fieldAuth.authPasswordEditText.isEnabled = !isLoading

            fieldBtn.btnLogin.isEnabled = !isLoading
            fieldBtn.tvLogin.visibility = if (isLoading) View.GONE else View.VISIBLE
            fieldBtn.progressLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
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