package com.example.speedify.feature_authentication.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_authentication.data.model.User
import com.example.speedify.feature_authentication.domain.interface_repository.AuthRepository

class GetCurrentUser(private val authRepository: AuthRepository) {
    operator fun invoke(): LiveData<ResultState<User?>> {
        return authRepository.getCurrentUser()
    }
}