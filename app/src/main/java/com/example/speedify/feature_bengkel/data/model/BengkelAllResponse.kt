package com.example.speedify.feature_bengkel.data.model

import com.google.gson.annotations.SerializedName

data class BengkelAllResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String
)

data class DataItem(

	@field:SerializedName("foto_url")
	val fotoUrl: String,

	@field:SerializedName("nama_bengkel")
	val namaBengkel: String,

	@field:SerializedName("spesialisasi_bengkel")
	val spesialisasiBengkel: String,

	@field:SerializedName("phone_bengkel")
	val phoneBengkel: String,

	@field:SerializedName("is_open")
	val isOpen: Boolean,

	@field:SerializedName("lokasi")
	val lokasi: String,

	@field:SerializedName("jenis_bengkel")
	val jenisBengkel: String,

	@field:SerializedName("rating")
	val rating: List<RatingItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("alamat")
	val alamat: String
)

data class RatingItem(

	@field:SerializedName("review_count")
	val reviewCount: Int,

	@field:SerializedName("average_rating")
	val averageRating: Any
)
