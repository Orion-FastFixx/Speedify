package com.example.speedify.feature_activity.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.feature_activity.domain.entity.PesananEntity
import com.example.speedify.feature_activity.domain.interface_repositoty.PesananRepo
import com.example.speedify.utils.ResultState

class GetPesananSelesai(private val repository: PesananRepo) {
    suspend operator fun invoke(): LiveData<ResultState<List<PesananEntity>>>{
        return repository.getPesananSelesai()
    }
}