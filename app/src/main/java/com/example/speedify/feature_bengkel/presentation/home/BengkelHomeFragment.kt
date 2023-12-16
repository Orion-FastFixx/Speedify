package com.example.speedify.feature_bengkel.presentation.home

import android.content.ContentValues.TAG
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
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.databinding.FragmentBengkelHomeBinding
import com.example.speedify.feature_bengkel.presentation.adapter.SectionTwoAdapter
import com.example.speedify.feature_bengkel.presentation.bengkel_mobil.BengkelMobilActivity
import com.example.speedify.feature_bengkel.presentation.bengkel_motor.BengkelMotorActivity
import com.example.speedify.feature_bengkel.presentation.home.adapter.PromotionAdapter
import com.example.speedify.feature_bengkel.presentation.home.adapter.SectionOneAdapter
import com.example.speedify.feature_bengkel.presentation.home.adapter.SectionThreeAdapter
import com.example.speedify.feature_bengkel.presentation.home.view_model.BengkelHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BengkelHomeFragment : Fragment() {
    private var _binding: FragmentBengkelHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BengkelHomeViewModel by viewModels()

    @Inject
    lateinit var userDataStore: UserDataStoreImpl

    private val promotionAdapter by lazy {
        PromotionAdapter()
    }

    private val sectionOneAdapter by lazy {
        SectionOneAdapter()
    }

    private val sectionTwoAdapter by lazy {
        SectionTwoAdapter()
    }

    private val sectionThreeAdapter by lazy {
        SectionThreeAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBengkelHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeBengkelState()


        val btnBengkelMobil = binding.btnBengkelMobil
        val btnBengkelMotor = binding.btnBengkelMotor

        btnBengkelMobil.setOnClickListener {
            val intent = Intent(requireContext(), BengkelMobilActivity::class.java)
            startActivity(intent)
        }

        btnBengkelMotor.setOnClickListener {
            val intent = Intent(requireContext(), BengkelMotorActivity::class.java)
            startActivity(intent)
        }
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
        binding.rvSectionThree.apply {
            adapter = sectionThreeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeBengkelState() {
        lifecycleScope.launch {
            try {
                viewModel.bengkelState.collect() { state ->
                    if (state.isLoading) {
                        Log.d(TAG, "BengkelHome:   Loading")
                    } else if (state.error != null) {
                        Log.e(TAG, "BengkelHome:   ${state.error}")
                    } else {
                        state.promotion?.let {
                            promotionAdapter.setItems(it)
                        }
                        state.theBestBengkelMobil?.let {
                            sectionOneAdapter.setItems(it)
                        }
                        state.officialBengkelMobil?.let {
                            sectionTwoAdapter.setItems(it)
                        }
                        state.publicBengkelMobil?.let {
                            sectionThreeAdapter.setItems(it)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "observeBengkelState: ${e.message}")
            }
        }
    }
}