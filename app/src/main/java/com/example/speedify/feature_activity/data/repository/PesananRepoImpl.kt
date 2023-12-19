package com.example.speedify.feature_activity.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.feature_activity.domain.interface_repositoty.PesananRepo

import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_activity.data.model.OrderItem
import com.example.speedify.feature_activity.data.remote.ActivityApi
import com.example.speedify.feature_education.data.model.ContentItem
import javax.inject.Inject

class PesananRepoImpl @Inject constructor(
    private val activityApi: ActivityApi,
    private val dataStore: UserDataStoreImpl
) : PesananRepo {

    override suspend fun getAllPesanan(): LiveData<ResultState<List<OrderItem>>> =
        liveData {
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }
                val response = activityApi.getAllPesanan(token)
                val Items = response.data
                emit(ResultState.Success(Items))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getPesananProses(): LiveData<ResultState<List<OrderItem>>> =
        liveData {
            try{
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = activityApi.getPesananProses(token)
                val filteredResponse =
                    response.data.filter { proses ->
                        proses.orderStatusId == 2
                    }
                emit(ResultState.Success(filteredResponse))
            }catch (e: Exception){
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getPesananSelesai(): LiveData<ResultState<List<OrderItem>>> =
        liveData {
            try{
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = activityApi.getPesananSelesai(token)
                val filteredResponse =
                    response.data.filter { proses ->
                        proses.orderStatusId == 4
                    }
                emit(ResultState.Success(filteredResponse))
            }catch (e: Exception){
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getPesananBatal(): LiveData<ResultState<List<OrderItem>>> =
        liveData {
            try{
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = activityApi.getPesananCancel(token)
                val filteredResponse =
                    response.data.filter { proses ->
                        proses.orderStatusId == 5
                    }
                emit(ResultState.Success(filteredResponse))
            }catch (e: Exception){
                emit(ResultState.Error(e.message.toString()))
            }
        }
}