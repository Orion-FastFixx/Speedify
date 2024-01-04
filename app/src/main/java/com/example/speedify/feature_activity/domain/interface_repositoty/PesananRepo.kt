package com.example.speedify.feature_activity.domain.interface_repositoty

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_activity.data.model.OrderItem

interface PesananRepo {
    suspend fun getAllPesanan(): LiveData<ResultState<List<OrderItem>>>
    suspend fun getPesananProses(): LiveData<ResultState<List<OrderItem>>>
    suspend fun getPesananBatal(): LiveData<ResultState<List<OrderItem>>>
    suspend fun getPesananSelesai(): LiveData<ResultState<List<OrderItem>>>
}