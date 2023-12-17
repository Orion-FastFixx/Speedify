package com.example.speedify.feature_bengkel.data.remote

import com.example.speedify.feature_bengkel.data.model.BengkelAllResponse
import com.example.speedify.feature_bengkel.data.model.DetailBengkelResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BengkelApi {

    @GET("pengendara/get-all-bengkel")
    suspend fun getAllBengkel(
        @Header("Authorization") token: String,
    ): BengkelAllResponse

    @GET("pengendara/get-detail-bengkel/{id}")
    suspend fun getDetailBengkel(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DetailBengkelResponse
}