package com.example.speedify.core.domain.entity

data class UserPreferences(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val roleId: Int? = null,
    val token: String? = null,
    val refreshToken: String? = null,
)
