package com.example.speedify.feature_bengkel.presentation.detail_bengkel

import com.example.speedify.feature_bengkel.domain.entity.LayananEntity

data class DetailBengkelState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val layanan : List<LayananEntity>? = null
)
