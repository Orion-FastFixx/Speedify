package com.example.speedify.feature_bengkel.presentation.bengkel_mobil

import com.example.speedify.feature_bengkel.data.model.DataItem

data class BengkelMobilState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val bengkelMobil: List<DataItem>? = null,
)