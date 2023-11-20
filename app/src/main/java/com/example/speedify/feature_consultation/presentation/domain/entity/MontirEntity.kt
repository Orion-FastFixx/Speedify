package com.example.speedify.feature_consultation.presentation.domain.entity

import androidx.annotation.DrawableRes
import com.example.speedify.R
data class MontirEntity(
    val id: String,
    @DrawableRes val imgMontir: Int,
    val jenisMontir: String,
    val namaMontir: String,
    val jlhRating:  Number,
    val pengalaman: Int,
    val harga: Int,
    val jlhCostumer: Int,
)


val dummyMontir = listOf(
    MontirEntity(
        "1",
        R.drawable.montir,
        "Montir Motor",
        "Reza Rizki",
        4.5,
        10,
        35000,
        100,
    ),
    MontirEntity(
        "2",
        R.drawable.montir2,
        "Montir Mobil",
        "Raihan",
        5,
        8,
        40000,
        99,
    ),
    MontirEntity(
        "3",
        R.drawable.montir,
        "Montir Motor",
        "Omyza",
        4.7,
        10,
        40000,
        200,
    )
)
