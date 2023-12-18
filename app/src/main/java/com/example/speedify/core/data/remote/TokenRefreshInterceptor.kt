package com.example.speedify.core.data.remote

import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.feature_authentication.data.remote.AuthApi
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenRefreshInterceptor @Inject constructor(
    private val dataStore: UserDataStoreImpl,
    private val authenticator: AuthApi,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val response = chain.proceed(originRequest)

        if (response.code == 401) {
            val refreshToken = runBlocking {
                dataStore.getUser().refreshToken
            }

            refreshToken?.let {
                val newTokenResponse = runBlocking {
                    authenticator.generateNewToken(it)
                }

                if (newTokenResponse.message == "Token refreshed") {
                    runBlocking {
                        dataStore.saveUserToken(newTokenResponse.token)
                    }
                    ApiConfig.TOKEN = newTokenResponse.token

                    val newRequest = originRequest.newBuilder()
                        .header("Authorization", "Bearer ${newTokenResponse.token}")
                        .build()
                    response.close()
                    return chain.proceed(newRequest)
                }
            }
        }
        return response
    }
}