package com.example.speedify.feature_activity.domain.entity

import androidx.annotation.DrawableRes
import com.example.speedify.R
import java.time.LocalDate
import java.util.EnumSet



data class PesananEntity(
    val id: String,
    @DrawableRes val imgPesanan: Int,
    val tgl: LocalDate,
    val namaBengkel: String,
    val kendala: String,
    val codeConfirm: Number,
    val status: Status,
    val harga: Number
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
        LocalDate.of(2023, 11, 22),
        "Sriwijaya Motor",
        "Ganti Spare Part",
        122930,
        Status.Proses,
        50000
    ),
    PesananEntity(
        "4",
        R.drawable.pesanan2,
        LocalDate.of(2023, 11, 22),
        "Center Mobil",
        "Ganti Spare Part",
        122929,
        Status.Batal,
        50000
    ),
    PesananEntity(
        "3",
        R.drawable.pesanan,
        LocalDate.of(2023, 9, 12),
        "Boboiboy Motor",
        "Mesin Mati",
        122920,
        Status.Selesai,
        55000
    ),
    PesananEntity(
        "5",
        R.drawable.pesanan2,
        LocalDate.of(2023, 11, 19),
        "Center Mobil",
        "Ban Kempes",
        122928,
        Status.Selesai,
        45000
    ),
    PesananEntity(
        "6",
        R.drawable.pesanan,
        LocalDate.of(2023, 8, 12),
        "Center Mobil",
        "Ban Bocor",
        122925,
        Status.Selesai,
        50000
    ),
)