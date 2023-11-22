package com.example.speedify.feature_bengkel.presentation.bengkel_mobil

import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity

data class BengkelMobilState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val bengkelMobil: List<BengkelEntity>? = null,
)