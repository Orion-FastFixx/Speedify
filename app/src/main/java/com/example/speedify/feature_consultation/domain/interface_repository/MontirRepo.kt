package com.example.speedify.feature_consultation.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.core.utils.ResultState

interface MontirRepo {
    suspend fun getAllMontir(): LiveData<ResultState<List<MontirEntity>>>
    suspend fun getTheBestMontir(): LiveData<ResultState<List<MontirEntity>>>
    suspend fun getTrustedMontir(): LiveData<ResultState<List<MontirEntity>>>
}