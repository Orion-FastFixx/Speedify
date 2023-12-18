package com.example.speedify.feature_authentication.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_authentication.data.model.LoginResponse
import com.example.speedify.feature_authentication.data.model.LogoutResponse
import com.example.speedify.feature_authentication.data.model.RefreshTokenResponse
import com.example.speedify.feature_authentication.data.model.RegisterResponse
import com.example.speedify.feature_authentication.data.model.User

interface AuthRepository {
    suspend fun signIn(email: String, password: String): LiveData<ResultState<LoginResponse>>
    suspend fun signUp(
        email: String,
        password: String,
        username: String,
        roleId: Int
    ): LiveData<ResultState<RegisterResponse>>

    fun getCurrentUser(): LiveData<ResultState<User?>>

    // suspend fun generateNewToken(refreshToken: String): LiveData<ResultState<RefreshTokenResponse>>

    suspend fun signOut(): LiveData<ResultState<LogoutResponse>>
}