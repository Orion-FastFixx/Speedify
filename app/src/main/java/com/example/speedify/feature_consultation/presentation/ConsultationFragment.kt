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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.R
import com.example.speedify.databinding.FragmentConsultationBinding
import com.example.speedify.feature_activity.domain.entity.PesananEntity
import com.example.speedify.feature_activity.presentation.adapter.PesananAdapter
import com.example.speedify.feature_activity.presentation.detail_pesanan.DetailPesananActivity
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.feature_consultation.presentation.adapter.MontirAdapter
import com.example.speedify.feature_consultation.presentation.order_montir.orderMontir
import com.example.speedify.feature_consultation.presentation.view_model.ConsultationViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        val state = viewModel.montirState.value

        if (state.isLoading) {
            Log.d(ContentValues.TAG, "Montir:   Loading")
        } else if (state.error != null) {
            Log.e(ContentValues.TAG, "Montir:   ${state.error}")
        } else {
            state.montir?.let {
                montirAdapter.setItems(it)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        montirAdapter.setOnItemClickCallback(object : MontirAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MontirEntity) {
                // Tanggapi klik item di sini
                val intent = Intent(requireContext(), orderMontir::class.java)
                startActivity(intent)
            }
        })
        
        val svConsultation = binding.svMontir.svFastfixx
        svConsultation.queryHint = resources.getString(R.string.search_montir)

        initAdapter()
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
