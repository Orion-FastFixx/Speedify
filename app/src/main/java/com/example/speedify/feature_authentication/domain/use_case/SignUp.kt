package com.example.speedify.feature_authentication.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_authentication.data.model.RegisterResponse
import com.example.speedify.feature_authentication.data.model.UserRegister
import com.example.speedify.feature_authentication.domain.interface_repository.AuthRepository

class SignUp(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        email: String,
        password: String,
        username: String,
        roleId: Int
    ): LiveData<ResultState<RegisterResponse>> {
        return authRepository.signUp( email, password, username, roleId)
    }
}