package com.example.speedify.feature_consultation.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.model.OrderBengkelServiceResponse
import com.example.speedify.feature_consultation.data.model.DaftarItem
import com.example.speedify.feature_consultation.data.model.OrderMontirServiceResponse

interface MontirRepo {
    suspend fun getAllMontir(): LiveData<ResultState<List<DaftarItem>>>
    suspend fun orderMontirService(
        montirId: Int,
        serviceId: List<Int>,
    ): LiveData<ResultState<OrderMontirServiceResponse>>
}