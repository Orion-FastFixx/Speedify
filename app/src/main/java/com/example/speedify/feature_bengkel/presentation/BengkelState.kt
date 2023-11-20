package com.example.speedify.feature_bengkel.presentation

import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity
import com.example.speedify.feature_bengkel.domain.entity.PromotionEntity

data class BengkelState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val promotion: List<PromotionEntity>? = null,
    val nearestBengkelMobil: List<BengkelEntity>? = null,
    val theBestBengkelMobil: List<BengkelEntity>? = null,
)