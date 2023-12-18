package com.example.speedify.feature_bengkel.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class OrderBengkelServiceResponse(

	@field:SerializedName("data")
	val data: OrderBengkelService,

	@field:SerializedName("message")
	val message: String
) : Parcelable

@Parcelize
data class AdditionalInfo(

	@field:SerializedName("precise_location")
	val preciseLocation: String,

	@field:SerializedName("complaint")
	val complaint: String,

	@field:SerializedName("fullName")
	val fullName: String
) : Parcelable

@Parcelize
data class OrderBengkelService(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("pengendara_id")
	val pengendaraId: Int,

	@field:SerializedName("additional_info")
	val additionalInfo: AdditionalInfo,

	@field:SerializedName("order_status_id")
	val orderStatusId: Int,

	@field:SerializedName("total_harga")
	val totalHarga: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("bengkel_id")
	val bengkelId: Int,

	@field:SerializedName("services")
	val services: List<ServicesItemCheckout>,

	@field:SerializedName("updatedAt")
	val updatedAt: String
) : Parcelable

@Parcelize
data class ServicesItemCheckout(

	@field:SerializedName("layanan")
	val layanan: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("order_services")
	val orderServices: OrderServices
) : Parcelable

@Parcelize
data class OrderServices(

	@field:SerializedName("price")
	val price: Int
) : Parcelable
