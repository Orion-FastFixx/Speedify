package com.example.speedify.feature_education.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.feature_education.domain.interface_repository.EducationRepository
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_education.data.model.ContentItem
import com.example.speedify.feature_education.data.remote.EducationApi
import javax.inject.Inject

class EducationRepositoryImpl @Inject constructor(
    private val educationApi: EducationApi,
    private val dataStore: UserDataStoreImpl
) : EducationRepository {

    override suspend fun getAllEducation(): LiveData<ResultState<List<ContentItem>>> =
        liveData {
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }
                val response = educationApi.getAllEducation(token)
                val Items = response.data
                emit(ResultState.Success(Items))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }
    override suspend fun getEducationTips(): LiveData<ResultState<List<ContentItem>>> =
        liveData {
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = educationApi.getAllEducation(token)
                val filteredResponse =
                    response.data.filter { education ->
                        education.kategori == "Tips"
                    }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getEducationInterior(): LiveData<ResultState<List<ContentItem>>> =
        liveData {
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = educationApi.getAllEducation(token)
                val filteredResponse =
                    response.data.filter { education ->
                        education.kategori == "Interior"
                    }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getEducationExterior(): LiveData<ResultState<List<ContentItem>>> =
        liveData {
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = educationApi.getAllEducation(token)
                val filteredResponse =
                    response.data.filter { education ->
                        education.kategori == "Exterior"
                    }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getEducationMesin(): LiveData<ResultState<List<ContentItem>>> =
        liveData {
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = educationApi.getAllEducation(token)
                val filteredResponse =
                    response.data.filter { education ->
                        education.kategori == "Mesin"
                    }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }
}