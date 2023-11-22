package com.example.speedify.feature_activity.domain.use_case

data class PesananUseCase(
    val getAllPesanan: GetAllPesanan,
    val getPesananProses: GetPesananProses,
    val getPesananBatal: GetPesananBatal,
    val getPesananSelesai: GetPesananSelesai,
)
