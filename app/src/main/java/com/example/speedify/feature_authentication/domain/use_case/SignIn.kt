package com.example.speedify.feature_authentication.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_authentication.data.model.LoginResponse
import com.example.speedify.feature_authentication.domain.interface_repository.AuthRepository

class SignIn(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): LiveData<ResultState<LoginResponse>> {
        return authRepository.signIn(email, password)
    }
}