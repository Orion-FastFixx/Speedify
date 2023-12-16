package com.example.speedify.core.domain.repository

import com.example.speedify.core.domain.entity.UserPreferences

interface UserDataStoreRepository {
    suspend fun getUser(): UserPreferences
    suspend fun saveUserLogin(user: UserPreferences)
    suspend fun clearUserLogin()
    suspend fun saveUserToken(token: String)

}