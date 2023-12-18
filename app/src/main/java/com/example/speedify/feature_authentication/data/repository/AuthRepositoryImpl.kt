package com.example.speedify.feature_authentication.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.core.data.remote.ApiConfig
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_authentication.data.model.LoginResponse
import com.example.speedify.feature_authentication.data.model.LogoutResponse
import com.example.speedify.feature_authentication.data.model.RegisterResponse
import com.example.speedify.feature_authentication.data.model.User
import com.example.speedify.feature_authentication.data.remote.AuthApi
import com.example.speedify.feature_authentication.domain.interface_repository.AuthRepository
import com.example.speedify.feature_authentication.domain.mapper.userPreferencesToUser
import com.example.speedify.feature_authentication.domain.mapper.userToUserPreferences
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authenticator: AuthApi,
    private val dataStore: UserDataStoreImpl
) : AuthRepository {
    override suspend fun signIn(
        email: String,
        password: String
    ): LiveData<ResultState<LoginResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val loginResponse = authenticator.signIn(email, password)
                val token = loginResponse.user.token
                // save user to datastore
                val userPreference = userToUserPreferences(loginResponse, token)
                userPreference.let { dataStore.saveUserLogin(it) }
                ApiConfig.TOKEN = token
                emit(ResultState.Success(loginResponse))
            } catch (e: Exception) {
                Log.e(TAG, "login: ${e.message.toString()}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun signUp(
        email: String,
        password: String,
        username: String,
        roleId: Int
    ): LiveData<ResultState<RegisterResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val registerResponse = authenticator.signUp(email, password, username, roleId)
                emit(ResultState.Success(registerResponse))
            } catch (e: Exception) {
                Log.e(TAG, "signUp: ${e.message.toString()}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override fun getCurrentUser(): LiveData<ResultState<User?>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userDataStore = dataStore.getUser()

                if (userDataStore.email == null || userDataStore.email == "") {
                    emit(ResultState.Success(null))
                    return@liveData
                }

                val token = userDataStore.token ?: ""

                val user = userPreferencesToUser(userDataStore, token)
                dataStore.saveUserToken(token)
                ApiConfig.TOKEN = token
                emit(ResultState.Success(user))
            } catch (e: Exception) {
                Log.e(TAG, "getCurrentUser: ${e.message.toString()}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun signOut(): LiveData<ResultState<LogoutResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val logoutResponse = authenticator.signOut()
                dataStore.clearUserLogin()
                emit(ResultState.Success(logoutResponse))
            } catch (e: Exception) {
                Log.e(TAG, "signOut: ${e.message.toString()}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

    companion object {
        private const val TAG = "AuthRepositoryImpl"
    }
}