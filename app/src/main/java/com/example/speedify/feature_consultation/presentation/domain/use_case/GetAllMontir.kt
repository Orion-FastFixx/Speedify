package com.example.speedify.feature_consultation.presentation.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.feature_consultation.presentation.domain.entity.MontirEntity
import com.example.speedify.feature_consultation.presentation.domain.interface_repo.MontirRepo
import com.example.speedify.utils.ResultState
class GetAllMontir(private val repository: MontirRepo) {
    suspend operator fun invoke(): LiveData<ResultState<List<MontirEntity>>> {
        return repository.getAllMontir()
    }
}