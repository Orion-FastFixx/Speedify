package com.example.speedify.feature_authentication.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_authentication.data.model.LogoutResponse
import com.example.speedify.feature_authentication.domain.interface_repository.AuthRepository

class SignOut(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): LiveData<ResultState<LogoutResponse>> {
        return authRepository.signOut()
    }
}