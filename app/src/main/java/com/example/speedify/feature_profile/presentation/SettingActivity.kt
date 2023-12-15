package com.example.speedify.feature_profile.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.core.data.remote.ApiConfig
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
            logoutAction()
        }
        binding.icNextEdit.setOnClickListener {
            val intent = Intent(this, EditPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logoutAction() {
        lifecycleScope.launch {
            userDataStore.clearUserLogin()
            ApiConfig.TOKEN = ""

            // Navigate to the Login screen
            val intent = Intent(this@SettingActivity, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}