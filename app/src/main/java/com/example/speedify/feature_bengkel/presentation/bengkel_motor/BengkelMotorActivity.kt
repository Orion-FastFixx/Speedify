package com.example.speedify.feature_bengkel.presentation.bengkel_motor

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.R
import com.example.speedify.databinding.ActivityBengkelMotorBinding
import com.example.speedify.feature_bengkel.presentation.bengkel_motor.adapter.BengkelMotorAdapter
import com.example.speedify.feature_bengkel.presentation.bengkel_motor.view_model.BengkelMotorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BengkelMotorActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityBengkelMotorBinding

    private val binding get() = _binding

    private val viewModel: BengkelMotorViewModel by viewModels()

    private val bengkelMotorAdapter by lazy {
        BengkelMotorAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBengkelMotorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val state = viewModel.bengkelMotorState.value

        initAdapter()

        if (state.isLoading) {
            Log.d(ContentValues.TAG, "BengkelMotor:   Loading")
        } else if (state.error != null) {
            Log.e(ContentValues.TAG, "BengkelMotor:   ${state.error}")
        } else {
            state.bengkelMotor?.let {
                bengkelMotorAdapter.setItems(it)
            }
        }

        val svBengkelMotor = binding.svBengkelMotor.svFastfixx
        svBengkelMotor.queryHint = resources.getString(R.string.search_bengkel)
    }

    private fun initAdapter() {
        binding.rvBengkelMotor.apply {
            adapter = bengkelMotorAdapter
            layoutManager =
                LinearLayoutManager(this@BengkelMotorActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}