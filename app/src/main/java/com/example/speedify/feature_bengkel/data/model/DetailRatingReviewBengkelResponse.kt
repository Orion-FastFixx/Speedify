package com.example.speedify.feature_bengkel.data.model

import com.google.gson.annotations.SerializedName

data class DetailRatingReviewBengkelResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

data class ReviewSummary(

	@field:SerializedName("average_rating")
	val averageRating: Any,

	@field:SerializedName("rating_count")
	val ratingCount: Int
)

data class ReviewAllUser(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("review")
	val review: String,

	@field:SerializedName("pengendara")
	val pengendara: Pengendara,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("bengkel_id")
	val bengkelId: Int,

	@field:SerializedName("bengkel_rating")
	val bengkelRating: Number,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class Data(

	@field:SerializedName("review_all")
	val reviewAll: List<ReviewAllUser>,

	@field:SerializedName("review_summary")
	val reviewSummary: ReviewSummary
)

data class Pengendara(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("foto")
	val foto: String
)
