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
import com.example.speedify.databinding.FragmentTipsBinding
import com.example.speedify.feature_education.presentation.education.adapter.EducationAdapter
import com.example.speedify.feature_education.presentation.education.view_model.EducationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TipsFragment : Fragment() {

    private var _binding: FragmentTipsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EducationViewModel by viewModels()

    private val educationAdapter by lazy {
        EducationAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        observeTips()

        // educationAdapter.setOnItemClickCallback(object : EducationAdapter.OnItemClickCallback {
        //     override fun onItemClicked(data: ContentItem) {
        //         // Tanggapi klik item di sini
        //         val intent = Intent(requireContext(), DetailEducationActivity::class.java)
        //         intent.putExtra("EDUCATION_ID", data.id)
        //         startActivity(intent)
        //     }
        // })
    }

    private fun observeTips() {
        lifecycleScope.launch {
            try {
                viewModel.educationState.collect { state ->
                    if (state.isLoading) {
                        Log.d(ContentValues.TAG, "Education:   Loading")
                    } else if (state.error != null) {
                        Log.e(ContentValues.TAG, "Education:   ${state.error}")
                    } else {
                        setLoadingState(false)
                        state.educationTips?.let {
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
                rvTipsEducation.animateVisibility(false)
            } else {
                viewLoading.animateVisibility(false)
                rvTipsEducation.animateVisibility(true)
            }
        }
    }

    private fun initAdapter() {
        val spanCount = 2
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin)
        val includeEdge = true

        binding.rvTipsEducation.apply {
            adapter = educationAdapter
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            addItemDecoration(GridSpacingItemDecoration(spanCount, spacingInPixels, includeEdge))
        }
    }

}