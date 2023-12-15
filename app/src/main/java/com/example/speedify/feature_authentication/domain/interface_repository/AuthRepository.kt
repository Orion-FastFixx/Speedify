package com.example.speedify.feature_authentication.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_authentication.data.model.RegisterResponse
import com.example.speedify.feature_authentication.data.model.User
import com.example.speedify.feature_authentication.data.model.UserRegister

interface AuthRepository {
    suspend fun signIn(email: String, password: String): LiveData<ResultState<User?>>
    suspend fun signUp(
        email: String,
        password: String,
        username: String,
        roleId: Int
    ): LiveData<ResultState<RegisterResponse>>

    fun getCurrentUser(): LiveData<ResultState<User?>>
}