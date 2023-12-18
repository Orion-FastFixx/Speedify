package com.example.speedify.feature_consultation.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_consultation.data.model.DaftarItem

class GetAllMontir(private val repository: MontirRepo) {
    suspend operator fun invoke(): LiveData<ResultState<List<DaftarItem>>> {
        return repository.getAllMontir()
    }
}