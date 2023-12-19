package com.example.speedify.feature_consultation.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.model.PayOrderResponse
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo

class PayOrderService(private val repository: MontirRepo) {
    suspend operator fun invoke(
        orderId: Int,
        paymentMethodId: Int
    ): LiveData<ResultState<PayOrderResponse>> {
        return repository.payOrder(orderId, paymentMethodId)
    }
}