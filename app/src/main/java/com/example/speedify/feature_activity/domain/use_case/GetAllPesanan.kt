package com.example.speedify.feature_activity.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.feature_activity.domain.entity.PesananEntity
import com.example.speedify.feature_activity.domain.interface_repositoty.PesananRepo
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo
import com.example.speedify.utils.ResultState

class GetAllPesanan(private val repository: PesananRepo) {
    suspend operator fun invoke(): LiveData<ResultState<List<PesananEntity>>>{
        return repository.getAllPesanan()
    }
}