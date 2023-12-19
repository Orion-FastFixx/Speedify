package com.example.speedify.feature_bengkel.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.model.ReviewAllUser
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository

class GetDetailRatingReviewBengkel(private val repository: BengkelRepository) {
    suspend operator fun invoke(id: Int): LiveData<ResultState<List<ReviewAllUser>>> {
        return repository.getDetailRatingReviewBengkel(id)
    }
}