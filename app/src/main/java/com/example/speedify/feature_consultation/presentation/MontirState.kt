package com.example.speedify.feature_consultation.presentation

import com.example.speedify.feature_consultation.data.model.DaftarItem


data class MontirState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val montir: List<DaftarItem>? = null,
)
