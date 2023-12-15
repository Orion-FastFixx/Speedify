package com.example.speedify.feature_profile.presentation.setting

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.core.utils.ResultState
import com.example.speedify.databinding.ActivitySettingBinding
import com.example.speedify.databinding.FragmentSettingBinding
import com.example.speedify.feature_authentication.presentation.sign_in.SignInActivity
import com.example.speedify.feature_profile.presentation.edit_password.EditPasswordActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SettingActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySettingBinding
    private val binding get() = _binding

    private val viewModel: SettingViewModel by viewModels()

    @Inject
    lateinit var userDataStore: UserDataStoreImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Configuration()
    }

    private fun Configuration() {
        binding.icBack.setOnClickListener {
            val intent = Intent(this, FragmentSettingBinding::class.java)
            startActivity(intent)
        }

        binding.layoutMenu2.setOnClickListener {
            //     logout
            viewModel.signOut()
        }
        binding.icNextEdit.setOnClickListener {
            val intent = Intent(this, EditPasswordActivity::class.java)
            startActivity(intent)
        }

        observeSignOutResult()
    }

    private fun observeSignOutResult() {
        lifecycleScope.launch {
            try {
                viewModel.signOutState.observe(this@SettingActivity) { result ->
                    when (result) {
                        is ResultState.Loading -> {
                            Log.d(TAG, "observeSignOutResult: Loading")
                        }

                        is ResultState.Success -> {
                            Log.d(TAG, "observeSignOutResult: Success")
                            lifecycleScope.launch {
                                userDataStore.clearUserLogin()
                            }
                            // Navigate to the Login screen
                            val intent = Intent(this@SettingActivity, SignInActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            finish()

                        }

                        is ResultState.Error -> {
                            Log.d(TAG, "observeSignOutResult: Error")
                        }
                    }
                }

            } catch (e: Exception) {
                Log.e(TAG, "observeSignOutResult: ${e.message}")
            }
        }
    }
}