package com.example.speedify.feature_consultation.presentation.domain.interface_repo

import androidx.lifecycle.LiveData
import com.example.speedify.feature_consultation.presentation.domain.entity.MontirEntity
import com.example.speedify.utils.ResultState

interface MontirRepo {
    suspend fun getAllMontir(): LiveData<ResultState<List<MontirEntity>>>
}