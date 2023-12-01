package com.example.speedify.feature_bengkel.domain.entity

import java.io.Serializable

data class LayananEntity(
    val item_name: String,
    val harga: Int // Using Int assuming price is always in whole numbers
) : Serializable

val dummyLayanan = listOf(
    LayananEntity("Mesin Mati", 100000),
    LayananEntity("Ban Bocor", 30000),
    LayananEntity("Derek", 200000)// Add more Layanan objects as needed
)