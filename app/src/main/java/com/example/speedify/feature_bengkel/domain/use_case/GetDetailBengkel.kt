package com.example.speedify.feature_bengkel.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.model.DetailBengkel
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository

class GetDetailBengkel(private val repository: BengkelRepository) {
    suspend operator fun invoke(id: Int): LiveData<ResultState<DetailBengkel>> {
        return repository.getDetailBengkel(id)
    }
}