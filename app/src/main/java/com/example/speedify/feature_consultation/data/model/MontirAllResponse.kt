package com.example.speedify.feature_consultation.data.model

import com.google.gson.annotations.SerializedName

data class MontirAllResponse(

	@field:SerializedName("data")
	val data: List<DaftarItem>,

	@field:SerializedName("message")
	val message: String
)

data class DaftarItem(

	@field:SerializedName("foto_url")
	val fotoUrl: String,

	@field:SerializedName("jenis_montir")
	val jenisMontir: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("pengalaman")
	val pengalaman: Any,

	@field:SerializedName("rating")
	val rating: List<RatingItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("services")
	val services: List<ServicesItem>,

	@field:SerializedName("is_available")
	val isAvailable: Boolean
)

data class RatingItem(

	@field:SerializedName("review_count")
	val reviewCount: Int,

	@field:SerializedName("average_rating")
	val averageRating: Any
)

data class ServicesItem(

	@field:SerializedName("layanan")
	val layanan: String,

	@field:SerializedName("harga_layanan")
	val hargaLayanan: HargaLayanan,

	@field:SerializedName("id")
	val id: Int
)

data class HargaLayanan(

	@field:SerializedName("harga")
	val harga: Int
)
