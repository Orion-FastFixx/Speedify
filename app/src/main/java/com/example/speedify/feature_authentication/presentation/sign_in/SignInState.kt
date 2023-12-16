package com.example.speedify.feature_authentication.presentation.sign_in

import com.example.speedify.feature_authentication.data.model.LoginResponse

data class SignInState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val token : String? = null,
    val user: LoginResponse? = null,
)
