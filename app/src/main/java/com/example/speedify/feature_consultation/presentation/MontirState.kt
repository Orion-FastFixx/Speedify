package com.example.speedify.feature_consultation.presentation

import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity
import com.example.speedify.feature_consultation.domain.entity.MontirEntity

data class MontirState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val montir: List<MontirEntity>? = null,
    val theBestMontir: List<MontirEntity>? = null,
    val trustedMontir: List<MontirEntity>? = null,
)
