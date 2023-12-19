package com.example.speedify.feature_consultation.presentation

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
import com.example.speedify.R
import com.example.speedify.core.utils.animateVisibility
import com.example.speedify.databinding.FragmentConsultationBinding
import com.example.speedify.feature_consultation.data.model.DaftarItem
import com.example.speedify.feature_consultation.presentation.adapter.MontirAdapter
import com.example.speedify.feature_consultation.presentation.order_montir.OrderMontirActivity
import com.example.speedify.feature_consultation.presentation.view_model.ConsultationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConsultationFragment : Fragment() {

    private var _binding: FragmentConsultationBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ConsultationViewModel by viewModels()

    private val montirAdapter by lazy {
        MontirAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConsultationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        montirAdapter.setOnItemClickCallback(object : MontirAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DaftarItem) {
                // Tanggapi klik item di sini
                val intent = Intent(requireContext(), OrderMontirActivity::class.java)
                startActivity(intent)
            }
        })
        val svConsultation = binding.svMontir.svFastfixx
        svConsultation.queryHint = resources.getString(R.string.search_montir)

        initAdapter()
        observeMontir()
    }

    private fun observeMontir() {
        lifecycleScope.launch {
            try {
                viewModel.montirState.collect { state ->
                    if (state.isLoading) {
                        setLoadingState(true)
                        Log.d(ContentValues.TAG, "Montir:   Loading")
                    } else if (state.error != null) {
                        setLoadingState(false)
                        Log.e(ContentValues.TAG, "Montir:   ${state.error}")
                    } else {
                        setLoadingState(false)
                        state.montir?.let {
                            montirAdapter.setItems(it)
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
                recyleviewmontir.animateVisibility(false)
            } else {
                viewLoading.animateVisibility(false)
                recyleviewmontir.animateVisibility(true)
            }
        }
    }

    private fun initAdapter() {
        binding.recyleviewmontir.apply {
            adapter = montirAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {
    }
}
