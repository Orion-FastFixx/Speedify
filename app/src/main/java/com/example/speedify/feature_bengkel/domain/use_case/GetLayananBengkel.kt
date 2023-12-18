package com.example.speedify.feature_bengkel.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.model.ServicesItem
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository

class GetLayananBengkel(private val repository: BengkelRepository) {
    suspend operator fun invoke(id: Int): LiveData<ResultState<List<ServicesItem>>> {
        return repository.getLayananBengkel(id)
    }
}