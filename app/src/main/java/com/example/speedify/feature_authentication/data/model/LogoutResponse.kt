package com.example.speedify.feature_authentication.data.model

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

	@field:SerializedName("auth")
	val auth: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token")
	val token: Any
)
