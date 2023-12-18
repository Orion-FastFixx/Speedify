package com.example.speedify.feature_bengkel.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.model.OrderBengkelServiceResponse
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository

class OrderBengkelService(private val repository: BengkelRepository) {
    suspend operator fun invoke(
        bengkelId: Int,
        serviceId: List<Int>,
        additionalInfo: String,
        fullName: String,
        complaint: String,
    ): LiveData<ResultState<OrderBengkelServiceResponse>> {
        return repository.orderBengkelService(
            bengkelId,
            serviceId,
            additionalInfo,
            fullName,
            complaint
        )
    }
}