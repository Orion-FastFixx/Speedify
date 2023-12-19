package com.example.speedify.feature_education.presentation.education

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.speedify.R
import com.example.speedify.core.utils.GridSpacingItemDecoration
import com.example.speedify.core.utils.animateVisibility
import com.example.speedify.databinding.FragmentMesinBinding
import com.example.speedify.feature_education.presentation.education.adapter.EducationAdapter
import com.example.speedify.feature_education.presentation.education.view_model.EducationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MesinFragment : Fragment() {

    private var _binding: FragmentMesinBinding? = null

    private val binding get() = _binding!!

    private val viewModel: EducationViewModel by viewModels()

    private val educationAdapter by lazy {
        EducationAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMesinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observeMesin()
    }

    private fun observeMesin() {
        lifecycleScope.launch {
            try {
                viewModel.educationState.collect { state ->
                    if (state.isLoading) {
                        setLoadingState(true)
                        Log.d(ContentValues.TAG, "Education:   Loading")
                    } else if (state.error != null) {
                        setLoadingState(false)
                        Log.e(ContentValues.TAG, "Education:   ${state.error}")
                    } else {
                        setLoadingState(false)
                        state.educationMesin?.let {
                            educationAdapter.setItems(it)
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
                rvMesinEducation.animateVisibility(false)
            } else {
                viewLoading.animateVisibility(false)
                rvMesinEducation.animateVisibility(true)
            }
        }
    }

    private fun initAdapter() {
        val spanCount = 2
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin)
        val includeEdge = true

        binding.rvMesinEducation.apply {
            adapter = educationAdapter
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacingInPixels, includeEdge))
        }
    }


}