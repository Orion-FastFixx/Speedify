package com.example.speedify.feature_consultation.presentation.order_montir

import com.example.speedify.feature_bengkel.data.model.PayOrderResponse

data class OrderMontirState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val payOrderResponse: PayOrderResponse? = null
)
