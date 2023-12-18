package com.example.speedify.core.data.remote

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.speedify.BuildConfig.API_BASE_URL
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.feature_authentication.data.remote.AuthApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        var TOKEN = ""

        inline fun <reified T> getApiService(context: Context, userDataStore: UserDataStoreImpl): T {
            val chuckerInterceptor = ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val authApi = retrofit.create(AuthApi::class.java)
            val refreshTokenInterceptor = TokenRefreshInterceptor(userDataStore, authApi)

            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", "Bearer $TOKEN")
                    .build()
                chain.proceed(requestHeaders)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .addInterceptor(authInterceptor)
                .addInterceptor(refreshTokenInterceptor)
                .build()

            return retrofit.newBuilder().client(client).build().create(T::class.java)
        }
    }
}