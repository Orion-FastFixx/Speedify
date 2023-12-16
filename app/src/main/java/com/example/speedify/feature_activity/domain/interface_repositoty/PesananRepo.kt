package com.example.speedify.feature_activity.domain.interface_repositoty

import androidx.lifecycle.LiveData
import com.example.speedify.feature_activity.domain.entity.PesananEntity
import com.example.speedify.core.utils.ResultState

interface PesananRepo {
    suspend fun getAllPesanan() : LiveData<ResultState<List<PesananEntity>>>
    suspend fun getPesananProses() : LiveData<ResultState<List<PesananEntity>>>
    suspend fun getPesananBatal() : LiveData<ResultState<List<PesananEntity>>>
    suspend fun getPesananSelesai() : LiveData<ResultState<List<PesananEntity>>>
}