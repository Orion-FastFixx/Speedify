package com.example.speedify.feature_authentication.domain.mapper

import com.example.speedify.core.domain.entity.UserPreferences
import com.example.speedify.feature_authentication.data.model.User

fun userToUserPreferences(user: User, token: String): UserPreferences {
    return UserPreferences(
        id = user.id,
        name = user.username,
        email = user.email,
        roleId = user.roleId,
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