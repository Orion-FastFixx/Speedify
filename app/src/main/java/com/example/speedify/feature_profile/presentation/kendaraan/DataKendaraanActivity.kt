package com.example.speedify.feature_profile.presentation.kendaraan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.speedify.R
import com.example.speedify.databinding.ActivityDataKendaraanBinding
import com.example.speedify.databinding.ActivityEditKendaraanBinding

class DataKendaraanActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDataKendaraanBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDataKendaraanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Configuration()
    }

    private fun Configuration() {
        binding.layoutMotor.setOnClickListener {
            val intent = Intent(this, EditKendaraanMotorActivity::class.java)
            startActivity(intent)
        }

        binding.layoutMobil.setOnClickListener {
            val intent = Intent(this, EditKendaraanMobilActivity::class.java)
            startActivity(intent)
        }
    }
}