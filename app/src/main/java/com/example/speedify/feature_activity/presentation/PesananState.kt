package com.example.speedify.feature_activity.presentation

import com.example.speedify.feature_activity.data.model.OrderItem

data class PesananState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val pesanan: List<OrderItem>? = null,
    val proses: List<OrderItem>? = null,
    val selesai: List<OrderItem>? = null,
    val batal: List<OrderItem>? = null,
    val mobil: List<OrderItem>? = null,
    val motor: List<OrderItem>? = null,
)
