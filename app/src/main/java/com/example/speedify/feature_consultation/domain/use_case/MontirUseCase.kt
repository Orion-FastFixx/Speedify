package com.example.speedify.feature_consultation.domain.use_case


data class MontirUseCase(
    val getAllMontir: GetAllMontir,
    val orderMontirService: OrderMontirService,
    val payOrderMontirService: PayOrderMontirService
)
