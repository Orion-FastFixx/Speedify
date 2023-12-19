package com.example.speedify.feature_activity.data.model

import com.google.gson.annotations.SerializedName

data class OrderAllResponse(

	@field:SerializedName("data")
	val data: List<OrderItem>,

	@field:SerializedName("message")
	val message: String
)

data class OrderItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("montir")
	val montir: Montir,

	@field:SerializedName("additional_info")
	val additionalInfo: AdditionalInfo,

	@field:SerializedName("order_status_id")
	val orderStatusId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("services")
	val services: List<ServicesItem>,

	@field:SerializedName("bengkel")
	val bengkel: Bengkel,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class Bengkel(

	@field:SerializedName("nama_bengkel")
	val namaBengkel: String
)

data class OrderServices(

	@field:SerializedName("price")
	val price: String
)

data class Montir(

	@field:SerializedName("nama")
	val nama: String
)

data class AdditionalInfo(

	@field:SerializedName("precise_location")
	val preciseLocation: String,

	@field:SerializedName("complaint")
	val complaint: String,

	@field:SerializedName("fullName")
	val fullName: String
)

data class ServicesItem(

	@field:SerializedName("layanan")
	val layanan: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("order_services")
	val orderServices: OrderServices
)
