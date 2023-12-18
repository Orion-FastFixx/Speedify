package com.example.speedify.feature_bengkel.presentation.checkout_bengkel

import com.example.speedify.feature_bengkel.data.model.PayOrderResponse

data class CheckoutBengkelState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val payOrderResponse: PayOrderResponse? = null
)
