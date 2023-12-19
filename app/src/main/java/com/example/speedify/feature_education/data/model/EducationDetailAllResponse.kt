package com.example.speedify.feature_education.data.model

import com.google.gson.annotations.SerializedName

data class EducationDetailAllResponse(

	@field:SerializedName("data")
	val data: DetailItem,

	@field:SerializedName("message")
	val message: String
)

data class DetailItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("foto_url")
	val fotoUrl: String,

	@field:SerializedName("sub_judul")
	val subJudul: String,

	@field:SerializedName("kategori")
	val kategori: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("jenis_kendaraan")
	val jenisKendaraan: String,

	@field:SerializedName("judul")
	val judul: String,

	@field:SerializedName("isi_konten")
	val isiKonten: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
