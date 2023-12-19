package com.example.speedify.feature_activity.data.remote

import com.example.speedify.feature_activity.data.model.OrderAllResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ActivityApi {

    @GET("pengendara/get-order-service")
    suspend fun getAllPesanan(
        @Header("Authorization") token: String,
    ): OrderAllResponse

}