package com.example.speedify.feature_consultation.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.databinding.FragmentTerpercayaBinding
import com.example.speedify.feature_consultation.presentation.adapter.MontirAdapter
import com.example.speedify.feature_consultation.presentation.view_model.ConsultationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TerpercayaFragment : Fragment() {

    private var _binding: FragmentTerpercayaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ConsultationViewModel by viewModels()

    private val montirAdapter by lazy {
        MontirAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTerpercayaBinding.inflate(inflater, container, false)

        // Pindahkan initAdapter() ke sini agar diinisialisasi saat fragment dibuat
        initAdapter()

        val state = viewModel.montirState.value

        if (state.isLoading) {
            Log.d("Montir", "Loading")
        } else if (state.error != null) {
            Log.e("Montir", state.error!!)
        } else {
            state.trustedMontir?.let {
                montirAdapter.setItems(it)
            }
        }
        return binding.root
    }

    private fun initAdapter() {
        binding.recyleviewmontir3.apply {
            adapter = montirAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Hapus initAdapter() dari sini, karena sudah dipindahkan ke onCreateView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
    }
}
