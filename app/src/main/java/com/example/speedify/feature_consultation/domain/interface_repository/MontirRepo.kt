package com.example.speedify.feature_consultation.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_consultation.data.model.DaftarItem

interface MontirRepo {
    suspend fun getAllMontir(): LiveData<ResultState<List<DaftarItem>>>
}