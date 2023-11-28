package com.example.speedify.feature_activity.domain.entity

import androidx.annotation.DrawableRes
import com.example.speedify.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.EnumSet



data class PesananEntity(
    val id: String,
    @DrawableRes val imgPesanan: Int,
    val namaBengkel: String,
    val tujuan: String,
    val tgl: LocalDateTime,
    val harga: Number,
    val status: Status,
    val rating: Int = 0,
)

enum class Status {
    Proses,
    Batal,
    Selesai,
}


val dummyPesanan = listOf(
    PesananEntity(
        "1",
        R.drawable.pesanan,
        "Sriwijaya Motor",
        "Kalidoni",
        LocalDateTime.of(2023, 11, 22, 0, 30,0).withHour(14),
        150000,
        Status.Proses,

    ),
    PesananEntity(
        "2",
        R.drawable.pesanan2,
        "CarFix Group",
        "Kalidoni",
        LocalDateTime.of(2023, 8, 10, 11, 30,0).withHour(14),
        150000,
        Status.Batal,
        rating = 0
    ),
    PesananEntity(
        "3",
        R.drawable.pesanan,
        "Honda Onion",
        "Kalidoni",
        LocalDateTime.of(2023, 10, 19, 23, 30,0).withHour(14),
        150000,
        Status.Selesai,
        rating = 5
    ),
    PesananEntity(
        "5",
        R.drawable.pesanan2,
        "Jasa Raya",
        "Kalidoni",
        LocalDateTime.of(2023, 7, 29, 8, 30,0).withHour(14),
        150000,
        Status.Selesai,
        rating = 4
    ),
    PesananEntity(
        "6",
        R.drawable.pesanan,
        "Honda Onion",
        "Kalidoni",
        LocalDateTime.of(2023, 2, 17, 12, 30,0),
        150000,
        Status.Selesai,
        rating = 5
    ),
)