package com.example.speedify.feature_education.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.speedify.feature_education.domain.entity.EducationEntity
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_education.data.model.ContentItem
import com.example.speedify.feature_education.data.model.DetailItem

interface EducationRepository {
    suspend fun getAllEducation(): LiveData<ResultState<List<ContentItem>>>
    suspend fun getEducationTips(): LiveData<ResultState<List<ContentItem>>>
    suspend fun getEducationInterior(): LiveData<ResultState<List<ContentItem>>>
    suspend fun getEducationExterior(): LiveData<ResultState<List<ContentItem>>>
    suspend fun getEducationMesin(): LiveData<ResultState<List<ContentItem>>>
    suspend fun getDetailEducation(id: Int): LiveData<ResultState<DetailItem>>
}