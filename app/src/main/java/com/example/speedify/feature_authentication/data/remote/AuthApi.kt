package com.example.speedify.feature_authentication.data.remote

import com.example.speedify.feature_authentication.data.model.LoginResponse
import com.example.speedify.feature_authentication.data.model.LogoutResponse
import com.example.speedify.feature_authentication.data.model.RefreshTokenResponse
import com.example.speedify.feature_authentication.data.model.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("auth/signin")
    suspend fun signIn(
        @Field("identifier") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun signUp(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("username") username: String,
        @Field("role_id") roleId: Int
    ): RegisterResponse

    @FormUrlEncoded
    @POST("auth/generate-new-token")
    suspend fun generateNewToken(
        @Field("refreshToken") refreshToken: String
    ): RefreshTokenResponse

    @POST("auth/signout")
    suspend fun signOut(): LogoutResponse
}