package com.example.speedify.feature_consultation.presentation

import com.example.speedify.feature_bengkel.data.model.OrderBengkelServiceResponse
import com.example.speedify.feature_consultation.data.model.DaftarItem
import com.example.speedify.feature_consultation.data.model.OrderMontirServiceResponse


data class MontirState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val montir: List<DaftarItem>? = null,
    val orderMontirService: OrderMontirServiceResponse? = null
)
