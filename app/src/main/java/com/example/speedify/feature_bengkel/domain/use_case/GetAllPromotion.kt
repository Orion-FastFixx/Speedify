package com.example.speedify.feature_bengkel.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.feature_bengkel.domain.entity.PromotionEntity
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository
import com.example.speedify.utils.ResultState

class GetAllPromotion(private val repository: BengkelRepository) {
    suspend operator fun invoke(): LiveData<ResultState<List<PromotionEntity>>> {
        return repository.getALlPromotion()
    }
}