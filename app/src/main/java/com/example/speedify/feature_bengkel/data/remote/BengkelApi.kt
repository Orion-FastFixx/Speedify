package com.example.speedify.feature_bengkel.data.remote

import com.example.speedify.feature_bengkel.data.model.BengkelAllResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface BengkelApi {

    @GET("pengendara/get-all-bengkel")
    suspend fun getAllBengkel(
        @Header("Authorization") token: String,
    ): BengkelAllResponse
}