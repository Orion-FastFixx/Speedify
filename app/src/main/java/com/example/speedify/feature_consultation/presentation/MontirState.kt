package com.example.speedify.feature_consultation.presentation

import com.example.speedify.feature_consultation.domain.entity.MontirEntity

data class MontirState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val montir: List<MontirEntity>? = null,
)