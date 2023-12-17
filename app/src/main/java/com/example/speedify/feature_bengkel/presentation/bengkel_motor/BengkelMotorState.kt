package com.example.speedify.feature_bengkel.presentation.bengkel_motor

import com.example.speedify.feature_bengkel.data.model.DataItem

data class BengkelMotorState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val bengkelMotor: List<DataItem>? = null,
)