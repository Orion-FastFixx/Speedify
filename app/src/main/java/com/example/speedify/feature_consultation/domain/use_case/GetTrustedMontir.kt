package com.example.speedify.feature_consultation.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo
import com.example.speedify.core.utils.ResultState

class GetTrustedMontir(private val repository: MontirRepo) {
    suspend operator fun invoke(): LiveData<ResultState<List<MontirEntity>>> {
        return repository.getTrustedMontir()
    }
}