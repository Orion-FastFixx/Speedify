package com.example.speedify.feature_education.data.remote


import com.example.speedify.feature_education.data.model.EducationAllResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface EducationApi {
    @GET("pengendara/get-all-education")
    suspend fun getAllEducation(
        @Header("Authorization") token: String,
    ): EducationAllResponse
}