package com.example.speedify.feature_bengkel.data.remote

import com.example.speedify.feature_bengkel.data.model.BengkelAllResponse
import com.example.speedify.feature_bengkel.data.model.DetailBengkelResponse
import com.example.speedify.feature_bengkel.data.model.OrderBengkelServiceResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
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

    @FormUrlEncoded
    @POST("pengendara/order-bengkel-service")
    suspend fun orderBengkelService(
        @Header("Authorization") token: String,
        @Field("bengkel_id") bengkelId: Int,
        @Field("service_id") serviceId: List<Int>,
        @Field("precise_location") additionalInfo: String,
        @Field("fullName") fullName: String,
        @Field("complaint") complaint: String,
    ): OrderBengkelServiceResponse
}