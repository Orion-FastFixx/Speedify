package com.example.speedify.feature_bengkel.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository
import com.example.speedify.core.utils.ResultState

class GetTheBestBengkelMobil(private val repository: BengkelRepository) {
    suspend operator fun invoke(): LiveData<ResultState<List<BengkelEntity>>>{
        return repository.getTheBestBengkelMobil()
    }
}