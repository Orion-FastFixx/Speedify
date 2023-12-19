package com.example.speedify.feature_activity.data.remote

import com.example.speedify.feature_activity.data.model.OrderAllResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ActivityApi {

    @GET("pengendara/get-order-service")
    suspend fun getAllPesanan(
        @Header("Authorization") token: String,
    ): OrderAllResponse

    @GET("pengendara/get-paid-order-service")
    suspend fun getPesananProses(
        @Header("Authorization") token: String,
    ): OrderAllResponse

    @GET("pengendara/get-complete-service")
    suspend fun getPesananSelesai(
        @Header("Authorization") token: String,
    ): OrderAllResponse

    @GET("pengendara/get-cancel-order-service")
    suspend fun getPesananCancel(
        @Header("Authorization") token: String,
    ): OrderAllResponse

}