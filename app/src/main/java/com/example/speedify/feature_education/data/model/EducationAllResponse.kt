package com.example.speedify.feature_education.data.model

import com.google.gson.annotations.SerializedName

data class EducationAllResponse(

	@field:SerializedName("data")
	val data: List<ContentItem>,

	@field:SerializedName("message")
	val message: String
)

data class ContentItem(

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
