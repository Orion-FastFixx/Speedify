package com.example.speedify.feature_bengkel.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.model.PayOrderResponse
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository

class PayOrderService(private val repository: BengkelRepository) {
    suspend operator fun invoke(
        orderId: Int,
        paymentMethodId: Int
    ): LiveData<ResultState<PayOrderResponse>> {
        return repository.payOrder(orderId, paymentMethodId)
    }
}