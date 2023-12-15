package com.example.speedify.feature_education.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.speedify.feature_education.data.datasource.EducationDataSource
import com.example.speedify.feature_education.domain.entity.EducationEntity
import com.example.speedify.feature_education.domain.interface_repository.EducationRepository
import com.example.speedify.core.utils.ResultState

class EducationRepositoryImpl private constructor() : EducationRepository {
    override suspend fun getAllEducation(): LiveData<ResultState<List<EducationEntity>>> =
        liveData {
            try {
                val response = EducationDataSource.getAllEducation()
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    companion object {
        @Volatile
        private var instance: EducationRepositoryImpl? = null

        fun getInstance(): EducationRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: EducationRepositoryImpl().apply { instance = this }
            }
    }
}