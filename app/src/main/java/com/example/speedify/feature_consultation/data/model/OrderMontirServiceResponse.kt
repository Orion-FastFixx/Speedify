package com.example.speedify.feature_consultation.data.model

import android.os.Parcelable
import com.example.speedify.feature_bengkel.data.model.OrderBengkelService
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderMontirServiceResponse(

	@field:SerializedName("data")
	val data: OrderMontirService,

	@field:SerializedName("message")
	val message: String
): Parcelable
@Parcelize
data class OrderMontirService(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("pengendara_id")
	val pengendaraId: Int,

	@field:SerializedName("order_status_id")
	val orderStatusId: Int,

	@field:SerializedName("total_harga")
	val totalHarga: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("montir_id")
	val montirId: Int,

	@field:SerializedName("services")
	val services: List<ServicesItemMontir>,

	@field:SerializedName("updatedAt")
	val updatedAt: String
): Parcelable
@Parcelize
data class OrderServices(

	@field:SerializedName("price")
	val price: String
): Parcelable
@Parcelize
data class ServicesItemMontir(

	@field:SerializedName("layanan")
	val layanan: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("order_services")
	val orderServices: OrderServices
): Parcelable

