package com.example.speedify.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.speedify.core.domain.entity.UserPreferences
import com.example.speedify.core.domain.repository.UserDataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataStoreImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    UserDataStoreRepository {
    override suspend fun getUser(): UserPreferences {
        val user = dataStore.data.map {
            UserPreferences(
                id = it[USERID_KEY] ?: 0,
                name = it[NAME_KEY] ?: "",
                email = it[EMAIL_KEY] ?: "",
                roleId = it[ROLEID_KEY] ?: 0,
                token = it[TOKEN_KEY] ?: "",
                refreshToken = it[REFRESH_TOKEN_KEY] ?: ""
            )
        }.first()
        return user
    }

    override suspend fun saveUserLogin(user: UserPreferences) {
        dataStore.edit { preferences ->
            preferences[USERID_KEY] = user.id ?: 0
            preferences[NAME_KEY] = user.name ?: ""
            preferences[EMAIL_KEY] = user.email ?: ""
            preferences[ROLEID_KEY] = user.roleId ?: 0
            preferences[TOKEN_KEY] = user.token ?: ""
            preferences[REFRESH_TOKEN_KEY] = user.refreshToken ?: ""
        }
    }

    override suspend fun clearUserLogin() {
        dataStore.edit { preferences ->
            preferences[USERID_KEY] = 0
            preferences[NAME_KEY] = ""
            preferences[EMAIL_KEY] = ""
            preferences[ROLEID_KEY] = 0
            preferences[TOKEN_KEY] = ""
            preferences[REFRESH_TOKEN_KEY] = ""
        }
    }

    override suspend fun saveUserToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override suspend fun refreshToken(refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    companion object {
        val TOKEN_KEY = stringPreferencesKey("token")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refreshToken")
        private val NAME_KEY = stringPreferencesKey("name")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val ROLEID_KEY = intPreferencesKey("roleId")
        private val USERID_KEY = intPreferencesKey("id")
    }
}