package com.example.speedify.feature_bengkel.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.model.DataItem
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository

class GetBengkelMotorWithHighReview(private val repository: BengkelRepository) {
    suspend operator fun invoke(): LiveData<ResultState<List<DataItem>>> {
        return repository.getBengkelMotorWithHighReview()
    }
}