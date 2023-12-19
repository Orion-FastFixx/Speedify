package com.example.speedify.feature_education.data.remote


import com.example.speedify.feature_education.data.model.EducationAllResponse
import com.example.speedify.feature_education.data.model.EducationDetailAllResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface EducationApi {
    @GET("pengendara/get-all-education")
    suspend fun getAllEducation(
        @Header("Authorization") token: String,
    ): EducationAllResponse

    @GET("pengendara/get-detail-education/{id}")
    suspend fun getDetailEducation(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): EducationDetailAllResponse
}