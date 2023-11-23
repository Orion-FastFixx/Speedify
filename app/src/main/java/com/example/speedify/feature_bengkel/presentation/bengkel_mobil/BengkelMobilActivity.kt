package com.example.speedify.feature_bengkel.presentation.bengkel_mobil

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.R
import com.example.speedify.databinding.ActivityBengkelMobilBinding
import com.example.speedify.feature_bengkel.presentation.bengkel_mobil.adapter.BengkelMobilAdapter
import com.example.speedify.feature_bengkel.presentation.bengkel_mobil.view_model.BengkelMobilViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BengkelMobilActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityBengkelMobilBinding

    private val binding get() = _binding

    private val viewModel: BengkelMobilViewModel by viewModels()

    private val bengkelMobilAdapter by lazy {
        BengkelMobilAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBengkelMobilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val state = viewModel.bengkelMobilState.value

        initAdapter()

        if (state.isLoading) {
            Log.d(TAG, "BengkelMobil:   Loading")
        } else if (state.error != null) {
            Log.e(TAG, "BengkelMobil:   ${state.error}")
        } else {
            state.bengkelMobil?.let {
                bengkelMobilAdapter.setItems(it)
            }
        }

        val svBengkelMobil = binding.svBengkelMobil.svFastfixx
        svBengkelMobil.queryHint = resources.getString(R.string.search_bengkel)
    }

    private fun initAdapter() {
        binding.rvBengkelMobil.apply {
            adapter = bengkelMobilAdapter
            layoutManager =
                LinearLayoutManager(this@BengkelMobilActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}