package com.example.speedify.feature_bengkel.presentation.review_bengkel

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedify.databinding.ActivityBengkelReviewBinding
import com.example.speedify.feature_bengkel.data.model.DetailBengkel
import com.example.speedify.feature_bengkel.presentation.review_bengkel.adapter.BengkelReviewAdapter
import com.example.speedify.feature_bengkel.presentation.review_bengkel.view_model.BengkelReviewViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BengkelReviewActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityBengkelReviewBinding
    private val binding get() = _binding

    private val viewModel: BengkelReviewViewModel by viewModels()

    private val bengkelReviewAdapter by lazy {
        BengkelReviewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBengkelReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()

        val detailBengkelData = intent.getParcelableExtra<DetailBengkel>(EXTRA_DETAIL_BENGKEL)
        if (detailBengkelData != null) {
            viewModel.getDetailRatingReviewBengkel(detailBengkelData.id)
            observeBengkelReview(detailBengkelData)
        }

        val iconBack = binding.icBack
        iconBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeBengkelReview(detailBengkelData: DetailBengkel) {
        lifecycleScope.launch {
            try {
                viewModel.bengkelReviewState.collect { state ->
                    if (state.isLoading) {
                        // Show loading indicator
                        Log.d(TAG, "observeBengkelReview:   Loading")
                    } else if (state.error != null) {
                        // Handle error
                        Toast.makeText(this@BengkelReviewActivity, state.error, Toast.LENGTH_SHORT)
                            .show()
                    } else if (state.bengkelRatingReview != null) {
                        binding.apply {
                            tvOverallRating.text =
                                detailBengkelData.rating.firstOrNull()?.averageRating?.toString()
                                    ?: "0"
                            tvOverallReview.text = String.format(
                                "(%s ratings)",
                                detailBengkelData.rating.firstOrNull()?.reviewCount?.toString()
                                    ?: "0"
                            )
                        }
                        state.bengkelRatingReview.let {
                            bengkelReviewAdapter.setItems(it)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "observeBengkelReview: ${e.message}", e)
            }
        }
    }

    private fun initAdapter() {
        binding.rvRatingReview.apply {
            adapter = bengkelReviewAdapter
            layoutManager =
                LinearLayoutManager(this@BengkelReviewActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {
        const val EXTRA_DETAIL_BENGKEL = "extra_detail_bengkel"
    }
}

