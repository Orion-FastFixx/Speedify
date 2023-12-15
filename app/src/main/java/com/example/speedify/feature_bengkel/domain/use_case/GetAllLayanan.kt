package com.example.speedify.feature_bengkel.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.feature_bengkel.domain.entity.LayananEntity
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository
import com.example.speedify.core.utils.ResultState

class GetAllLayanan(private val repository: BengkelRepository) {
    suspend operator fun invoke(): LiveData<ResultState<List<LayananEntity>>> {
        return repository.getAllLayanan()
    }
}
