package com.example.speedify.core.domain.repository

interface BaseAuthRepository {
    suspend fun signIn(email: String, password: String): String?
    suspend fun getUserToken(): String?
    fun logout()
}