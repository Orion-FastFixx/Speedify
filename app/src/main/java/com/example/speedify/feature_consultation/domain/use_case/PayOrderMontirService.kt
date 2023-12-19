package com.example.speedify.feature_consultation.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_consultation.data.model.PayOrderMontirResponse
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo

class PayOrderMontirService(private val repository: MontirRepo) {
    suspend operator fun invoke(
        orderId: Int,
        paymentMethodId: Int
    ): LiveData<ResultState<PayOrderMontirResponse>> {
        return repository.payOrder(orderId, paymentMethodId)
    }
}