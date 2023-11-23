package com.example.speedify.feature_activity.presentation

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.R
import com.example.speedify.databinding.FragmentProsesBinding
import com.example.speedify.databinding.FragmentTersediaBinding
import com.example.speedify.feature_activity.presentation.adapter.PesananAdapter
import com.example.speedify.feature_activity.presentation.view_model.PesananViewModel
import com.example.speedify.feature_consultation.presentation.adapter.MontirAdapter
import com.example.speedify.feature_consultation.presentation.view_model.ConsultationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProsesFragment : Fragment() {

    private var _binding: FragmentProsesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PesananViewModel by viewModels()


    private val pesananAdapter by lazy {
        PesananAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProsesBinding.inflate(inflater, container, false)

        initAdapter()

        val state = viewModel.pesananState.value

        if (state.isLoading) {
            Log.d(ContentValues.TAG, "Pesanan:   Loading")
        } else if (state.error != null) {
            Log.e(ContentValues.TAG, "Pesanan:   ${state.error}")
        } else {
            state.proses?.let {
                pesananAdapter.setItems(it)
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initAdapter() {
        binding.rvProses.apply {
            adapter = pesananAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {

    }
}