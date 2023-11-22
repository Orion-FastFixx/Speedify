package com.example.speedify.feature_activity.presentation

import com.example.speedify.feature_activity.domain.entity.PesananEntity
import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity
import com.example.speedify.feature_consultation.domain.entity.MontirEntity

data class PesananState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val pesanan: List<PesananEntity>? = null,
    val proses: List<PesananEntity>? = null,
    val selesai: List<PesananEntity>? = null,
    val batal: List<PesananEntity>? = null,
    val mobil: List<PesananEntity>? = null,
    val motor: List<PesananEntity>? = null,
)
