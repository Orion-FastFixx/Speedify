package com.example.speedify.feature_education.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.speedify.feature_education.domain.entity.EducationEntity
import com.example.speedify.utils.ResultState

interface EducationRepository {

    suspend fun getAllEducation(): LiveData<ResultState<List<EducationEntity>>>
}