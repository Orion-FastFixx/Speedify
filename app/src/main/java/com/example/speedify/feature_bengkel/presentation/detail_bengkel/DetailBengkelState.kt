package com.example.speedify.feature_bengkel.presentation.detail_bengkel

import com.example.speedify.feature_bengkel.data.model.DetailBengkel
import com.example.speedify.feature_bengkel.data.model.ServicesItem

data class DetailBengkelState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val detailBengkel: DetailBengkel? = null,
    val layanan: List<ServicesItem>? = null
)
