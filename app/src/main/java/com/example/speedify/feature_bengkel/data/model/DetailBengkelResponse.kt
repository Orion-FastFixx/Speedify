package com.example.speedify.feature_bengkel.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailBengkelResponse(

    @field:SerializedName("data")
    val data: DetailBengkel,

    @field:SerializedName("message")
    val message: String
) : Parcelable

@Parcelize
data class ServicesItem(

    @field:SerializedName("layanan")
    val layanan: String,

    @field:SerializedName("harga_layanan")
    val hargaLayanan: HargaLayanan,

    @field:SerializedName("id")
    val id: Int
) : Parcelable

@Parcelize
data class DetailBengkel(

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
    val rating: List<RatingItemDetailBengkel>,


    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("deskripsi")
    val deskripsi: String,

    @field:SerializedName("services")
    val services: List<ServicesItem>,

    @field:SerializedName("alamat")
    val alamat: String
) : Parcelable

@Parcelize
data class RatingItemDetailBengkel(

    @field:SerializedName("review_count")
    val reviewCount: Int,

    @field:SerializedName("average_rating")
    val averageRating: Number
) : Parcelable

@Parcelize
data class HargaLayanan(

    @field:SerializedName("harga")
    val harga: Int
) : Parcelable
