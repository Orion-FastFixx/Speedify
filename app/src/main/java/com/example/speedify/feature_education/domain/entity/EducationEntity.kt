package com.example.speedify.feature_education.domain.entity

import androidx.annotation.DrawableRes
import com.example.speedify.R

data class EducationEntity(
    val id: String,
    @DrawableRes val gambar: Int,
    val nama: String,
    val jenis_kendaraan: String,
)

val dummyEducation = listOf(
    EducationEntity(
        "1",
        R.drawable.example_img_3,
        "Perawatan Jok",
        "Mobil",
    ),
    EducationEntity(
        "2",
        R.drawable.example_img_3,
        "Perawatan Panel",
        "Mobil",
    ),
    EducationEntity(
        "3",
        R.drawable.example_img_3,
        "Perawatan Mesin",
        "Mobil",
    ),
    EducationEntity(
        "4",
        R.drawable.example_img_3,
        "Perawatan Air Radiator",
        "Mobil",
    ),
    EducationEntity(
        "5",
        R.drawable.example_img_3,
        "Perawatan AC",
        "Mobil",
    ),
    EducationEntity(
        "6",
        R.drawable.example_img_3,
        "Perawatan Kaki-Kaki",
        "Mobil",
    ),
    EducationEntity(
        "7",
        R.drawable.example_img_3,
        "Perawatan Rem",
        "Mobil",
    ),
    EducationEntity(
        "8",
        R.drawable.example_img_3,
        "Perawatan Kelistrikan",
        "Mobil",
    ),
    EducationEntity(
        "9",
        R.drawable.example_img_3,
        "Perawatan Ban",
        "Mobil",
    ),
    EducationEntity(
        "10",
        R.drawable.example_img_3,
        "Perawatan Oli",
        "Mobil",
    ),
)