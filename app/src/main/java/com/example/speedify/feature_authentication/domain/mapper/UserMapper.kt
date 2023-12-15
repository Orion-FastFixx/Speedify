package com.example.speedify.feature_authentication.domain.mapper

import com.example.speedify.core.domain.entity.UserPreferences
import com.example.speedify.feature_authentication.data.model.LoginResponse
import com.example.speedify.feature_authentication.data.model.User

fun userToUserPreferences(user: LoginResponse, token: String): UserPreferences {
    return UserPreferences(
        id = user.user.id,
        name = user.user.username,
        email = user.user.email,
        roleId = user.user.roleId,
        token = token
    )
}

fun userPreferencesToUser(userPreferences: UserPreferences, token: String): User {
    return User(
        id = userPreferences.id ?: 0,
        username = userPreferences.name ?: "",
        email = userPreferences.email ?: "",
        roleId = userPreferences.roleId ?: 0,
        token = token
    )
}