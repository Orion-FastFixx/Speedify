package com.example.speedify.feature_consultation.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_consultation.data.model.OrderMontirServiceResponse
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo

class OrderMontirService(private val repository: MontirRepo) {
    suspend operator fun invoke(
        montirId: Int,
        serviceId: List<Int>,
    ): LiveData<ResultState<OrderMontirServiceResponse>> {
        return repository.orderMontirService(
            montirId,
            serviceId,
        )
    }
}