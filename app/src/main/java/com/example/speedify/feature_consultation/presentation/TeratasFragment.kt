package com.example.speedify.feature_consultation.presentation

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.R
import com.example.speedify.databinding.FragmentTeratasBinding
import com.example.speedify.feature_consultation.presentation.adapter.Montir2Adapter
import com.example.speedify.feature_consultation.presentation.adapter.MontirAdapter
import com.example.speedify.feature_consultation.presentation.view_model.ConsultationViewModel

class TeratasFragment : Fragment() {

    private var _binding: FragmentTeratasBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ConsultationViewModel by viewModels()

    private val montirAdapter by lazy {
        MontirAdapter()
    }

    private val montir2Adapter by lazy {
        Montir2Adapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeratasBinding.inflate(inflater, container, false)

        val state = viewModel.montirState.value

        if (state.isLoading) {
            Log.d(ContentValues.TAG, "Montir:   Loading")
        } else if (state.error != null) {
            Log.e(ContentValues.TAG, "Montir:   ${state.error}")
        } else {
            state.theBestMontir?.let {
                montir2Adapter.setItems(it)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        binding.recyleviewmontir2.apply {
            adapter = montirAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {

    }
}