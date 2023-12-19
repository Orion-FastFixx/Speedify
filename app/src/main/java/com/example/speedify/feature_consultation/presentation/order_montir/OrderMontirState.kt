package com.example.speedify.feature_consultation.presentation.order_montir

import com.example.speedify.feature_consultation.data.model.PayOrderMontirResponse

data class OrderMontirState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val payOrderMontirResponse: PayOrderMontirResponse? = null
)
