package com.example.speedify.feature_bengkel.domain.entity

import androidx.annotation.DrawableRes
import com.example.speedify.R

data class BengkelEntity(
    val id: String,
    val nama: String,
    val phone: String,
    val alamat: String,
    val lokasi: String,
    val deskripsi: String,
    @DrawableRes val foto: Int,
    val jenis_bengkel: String,
    val tipe_bengkel: String,
    val waktu: String,
    val is_open: Boolean,
    val rating: Number,
    val review: Int,
)

val dummyBengkel = listOf(
    BengkelEntity(
        "1",
        "Sriwijaya Motor",
        "08123456789",
        "Jl. Sriwijaya No. 1",
        "3.2",
        "Bengkel motor terbaik di kota Palembang",
        R.drawable.example_img,
        "Resmi",
        "Mobil",
        "30 mins",
        true,
        4.7,
        100
    ),
    BengkelEntity(
        "2",
        "Honda Union Motor",
        "08123456789",
        "Jl. Sriwijaya No. 1",
        "3.2",
        "Bengkel motor terbaik di kota Palembang",
        R.drawable.example_img,
        "Resmi",
        "Mobil",
        "30 mins",
        true,
        4.7,
        100
    ),
    BengkelEntity(
        "3",
        "MSC Motor",
        "08123456789",
        "Jl. Sriwijaya No. 1",
        "3.2",
        "Bengkel motor terbaik di kota Palembang",
        R.drawable.example_img,
        "Resmi",
        "Mobil",
        "30 mins",
        true,
        4.7,
        100
    ),
    BengkelEntity(
        "4",
        "Honda Maju Motor",
        "08123456789",
        "Jl. Sriwijaya No. 1",
        "3.2",
        "Bengkel motor terbaik di kota Palembang",
        R.drawable.example_img,
        "Resmi",
        "Mobil",
        "30 mins",
        true,
        4.7,
        100
    ),
    BengkelEntity(
        "5",
        "Astra Motor",
        "08123456789",
        "Jl. Sriwijaya No. 1",
        "3.2",
        "Bengkel motor terbaik di kota Palembang",
        R.drawable.example_img,
        "Resmi",
        "Mobil",
        "30 mins",
        true,
        4.7,
        100
    ),
    BengkelEntity(
        "6",
        "Ace Motor",
        "08123456789",
        "Jl. Sriwijaya No. 1",
        "3.2",
        "Bengkel motor terbaik di kota Palembang",
        R.drawable.example_img,
        "Resmi",
        "Mobil",
        "30 mins",
        true,
        4.7,
        100
    ),
)
