package com.example.speedify.feature_authentication.presentation.sign_up

import com.example.speedify.feature_authentication.data.model.RegisterResponse
import com.example.speedify.feature_authentication.data.model.UserRegister

data class SignUpState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: RegisterResponse? = null,
)
