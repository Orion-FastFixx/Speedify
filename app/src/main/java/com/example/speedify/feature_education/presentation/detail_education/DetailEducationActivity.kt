package com.example.speedify.feature_education.presentation.detail_education

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.databinding.ActivityDetailEducationBinding

class DetailEducationActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDetailEducationBinding

    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailEducationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val iconBack = binding.icBack
        iconBack.setOnClickListener {
            onBackPressed()
        }
    }
}