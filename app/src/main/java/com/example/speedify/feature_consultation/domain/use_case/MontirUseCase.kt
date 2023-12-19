package com.example.speedify.feature_consultation.domain.use_case

import com.example.speedify.feature_consultation.data.model.OrderMontirServiceResponse

data class MontirUseCase(
    val getAllMontir: GetAllMontir,
    val orderMontirService: OrderMontirService,
)
