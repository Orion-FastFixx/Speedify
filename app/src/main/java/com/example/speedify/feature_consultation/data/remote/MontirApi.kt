package com.example.speedify.feature_consultation.data.remote

import com.example.speedify.feature_bengkel.data.model.OrderBengkelServiceResponse
import com.example.speedify.feature_bengkel.data.model.PayOrderResponse
import com.example.speedify.feature_consultation.data.model.MontirAllResponse
import com.example.speedify.feature_consultation.data.model.OrderMontirServiceResponse
import com.example.speedify.feature_consultation.data.model.PayOrderMontirResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MontirApi {
    @GET("pengendara/get-all-montir")
    suspend fun getAllMontir(
        @Header("Authorization") token: String,
    ): MontirAllResponse

    @FormUrlEncoded
    @POST("pengendara/order-montir-service")
    suspend fun orderMontirService(
        @Header("Authorization") token: String,
        @Field("montir_id") montirId: Int,
        @Field("service_id") serviceId: List<Int>,
    ): OrderMontirServiceResponse

    @FormUrlEncoded
    @POST("pengendara/pay-order")
    suspend fun payOrder(
        @Header("Authorization") token: String,
        @Field("order_id") orderId: Int,
        @Field("payment_method_id") paymentMethodId: Int,
    ): PayOrderMontirResponse
}