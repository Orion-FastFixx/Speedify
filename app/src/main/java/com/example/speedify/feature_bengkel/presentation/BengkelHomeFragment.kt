package com.example.speedify.feature_bengkel.presentation

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.databinding.FragmentBengkelHomeBinding
import com.example.speedify.feature_bengkel.presentation.adapter.PromotionAdapter
import com.example.speedify.feature_bengkel.presentation.adapter.SectionOneAdapter
import com.example.speedify.feature_bengkel.presentation.adapter.SectionTwoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BengkelHomeFragment : Fragment() {
    private var _binding: FragmentBengkelHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BengkelHomeViewModel by viewModels()

    private val promotionAdapter by lazy {
        PromotionAdapter()
    }

    private val sectionOneAdapter by lazy {
        SectionOneAdapter()
    }

    private val sectionTwoAdapter by lazy {
        SectionTwoAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBengkelHomeBinding.inflate(inflater, container, false)

        val state = viewModel.bengkelState.value

        if (state.isLoading) {
            Log.d(TAG, "BengkelHome:   Loading")
        } else if (state.error != null) {
            Log.e(TAG, "BengkelHome:   ${state.error}")
        } else {
            state.promotion?.let {
                promotionAdapter.setItems(it)
            }
            state.bengkelMobil?.let {
                sectionOneAdapter.setItems(it)
            }
            state.bengkelMobil?.let {
                sectionTwoAdapter.setItems(it)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        binding.rvPromotions.apply {
            adapter = promotionAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rvSectionOne.apply {
            adapter = sectionOneAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rvSectionTwo.apply {
            adapter = sectionTwoAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}