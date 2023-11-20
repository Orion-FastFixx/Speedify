package com.example.speedify.feature_consultation.presentation.presentation

import android.os.Bundle
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.databinding.FragmentTersediaBinding
import com.example.speedify.feature_consultation.presentation.presentation.adapter.MontirAdapter
import com.example.speedify.feature_consultation.presentation.presentation.adapter.MyViewPagerAdapter
import com.example.speedify.feature_consultation.presentation.presentation.model.ConsultationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TersediaFragment : Fragment() {

    private var _binding: FragmentTersediaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ConsultationViewModel by viewModels()

    private val montirAdapter by lazy {
        MontirAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTersediaBinding.inflate(inflater, container, false)

        val state = viewModel.montirState.value

        if (state.isLoading) {
            Log.d(TAG, "Montir:   Loading")
        } else if (state.error != null) {
            Log.e(TAG, "Montir:   ${state.error}")
        } else {
            state.montir?.let {
                montirAdapter.setItems(it)
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        binding.recyleviewmontir.apply {
            adapter = montirAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
    }
}


