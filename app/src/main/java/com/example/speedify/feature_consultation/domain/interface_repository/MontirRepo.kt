package com.example.speedify.feature_consultation.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.utils.ResultState

interface MontirRepo {
    suspend fun getAllMontir(): LiveData<ResultState<List<MontirEntity>>>
}