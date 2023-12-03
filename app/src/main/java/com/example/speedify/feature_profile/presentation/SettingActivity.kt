package com.example.speedify.feature_profile.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.speedify.databinding.ActivitySettingBinding
import com.example.speedify.databinding.FragmentSettingBinding
import com.example.speedify.feature_profile.presentation.edit_password.EditPasswordActivity

class SettingActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySettingBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Configuration()
    }

    private fun Configuration(){
        binding.icBack.setOnClickListener {
            val intent = Intent(this, FragmentSettingBinding::class.java)
            startActivity(intent)
        }

        binding.icNextOut.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        binding.icNextEdit.setOnClickListener {
            val intent = Intent(this, EditPasswordActivity::class.java)
            startActivity(intent)
        }

//        binding.icNextOut.setOnClickListener {
//            val intent = Intent(this, SettingActivity::class.java)
//            startActivity(intent)
//        }
    }
}