package com.example.speedify.feature_consultation.data.remote

import com.example.speedify.feature_consultation.data.model.MontirAllResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface MontirApi {
    @GET("pengendara/get-all-montir")
    suspend fun getAllMontir(
        @Header("Authorization") token: String,
    ): MontirAllResponse
}