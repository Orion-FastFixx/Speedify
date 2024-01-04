package com.example.speedify.feature_activity.presentation

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.core.utils.animateVisibility
import com.example.speedify.databinding.FragmentCancelBinding
import com.example.speedify.feature_activity.data.model.OrderItem
import com.example.speedify.feature_activity.presentation.adapter.PesananAdapter
import com.example.speedify.feature_activity.presentation.detail_pesanan.DetailPesananActivity
import com.example.speedify.feature_activity.presentation.view_model.PesananViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CancelFragment : Fragment() {

    private var _binding: FragmentCancelBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PesananViewModel by viewModels()


    private val pesananAdapter by lazy {
        PesananAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCancelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeCancelOrder()

        pesananAdapter.setOnItemClickCallback(object : PesananAdapter.OnItemClickCallback {
            override fun onItemClicked(data: OrderItem) {
                // Tanggapi klik item di sini
                val intent = Intent(requireContext(), DetailPesananActivity::class.java)
                intent.putExtra("EDUCATION_ID", data.id)
                startActivity(intent)
            }
        })
    }

    private fun observeCancelOrder() {
        lifecycleScope.launch {
            try {
                viewModel.pesananState.collect { state ->
                    if (state.isLoading) {
                        setLoadingState(true)
                        Log.d(ContentValues.TAG, "Pesanan Batal:   Loading")
                    } else if (state.error != null) {
                        setLoadingState(false)
                        Log.e(ContentValues.TAG, "Pesanan Batal:   ${state.error}")
                    } else {
                        setLoadingState(false)
                        Log.d(ContentValues.TAG, "Pesanan Batal:   Success")
                        state.batal?.let {
                            pesananAdapter.setItems(it)
                        }
                    }
                }

            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "observeExterior: ${e.message}")
            }
        }
    }

    private fun setLoadingState(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                viewLoading.animateVisibility(true)
                rvCancel.animateVisibility(false)
            } else {
                viewLoading.animateVisibility(false)
                rvCancel.animateVisibility(true)
            }
        }
    }

    private fun initAdapter() {
        binding.rvCancel.apply {
            adapter = pesananAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
}