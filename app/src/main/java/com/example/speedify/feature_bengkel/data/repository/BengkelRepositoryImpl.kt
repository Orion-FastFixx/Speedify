package com.example.speedify.feature_bengkel.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.datasource.BengkelDataSource
import com.example.speedify.feature_bengkel.data.model.DataItem
import com.example.speedify.feature_bengkel.data.model.DetailBengkel
import com.example.speedify.feature_bengkel.data.model.OrderBengkelServiceResponse
import com.example.speedify.feature_bengkel.data.model.ServicesItem
import com.example.speedify.feature_bengkel.data.remote.BengkelApi
import com.example.speedify.feature_bengkel.domain.entity.PromotionEntity
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository
import javax.inject.Inject

class BengkelRepositoryImpl @Inject constructor(
    private val bengkelApi: BengkelApi,
    private val dataStore: UserDataStoreImpl
) : BengkelRepository {
    override suspend fun getALlPromotion(): LiveData<ResultState<List<PromotionEntity>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val response = BengkelDataSource.getAllPromotion()
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getAllBengkelMobil(): LiveData<ResultState<List<DataItem>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getAllBengkel(token)
                val filteredResponse =
                    response.data.filter { bengkel ->
                        bengkel.spesialisasiBengkel == "Bengkel Mobil"
                    }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getTheBestBengkelMobil(): LiveData<ResultState<List<DataItem>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getAllBengkel(token)
                val filteredResponse =
                    response.data.filter { bengkel ->
                        bengkel.spesialisasiBengkel == "Bengkel Mobil" && bengkel.rating.firstOrNull()?.averageRating?.let {
                            it is Number && it.toDouble() >= 4.5
                        } ?: false
                    }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getOfficialBengkelMobil(): LiveData<ResultState<List<DataItem>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getAllBengkel(token)
                val filteredResponse = response.data.filter { bengkel ->
                    bengkel.spesialisasiBengkel == "Bengkel Mobil" && bengkel.jenisBengkel == "Bengkel Resmi"
                }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getPublicBengkelMobil(): LiveData<ResultState<List<DataItem>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getAllBengkel(token)
                val filteredResponse = response.data.filter { bengkel ->
                    bengkel.spesialisasiBengkel == "Bengkel Mobil" && bengkel.jenisBengkel == "Bengkel Umum"
                }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getBengkelMobilWithHighReview(): LiveData<ResultState<List<DataItem>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getAllBengkel(token)
                val filteredResponse =
                    response.data.filter { bengkel ->
                        bengkel.spesialisasiBengkel == "Bengkel Mobil" && bengkel.rating.firstOrNull()?.reviewCount!! >= 100
                    }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getBengkelMotorWithHighReview(): LiveData<ResultState<List<DataItem>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getAllBengkel(token)
                val filteredResponse =
                    response.data.filter { bengkel ->
                        bengkel.spesialisasiBengkel == "Bengkel Motor" && bengkel.rating.firstOrNull()?.reviewCount!! >= 100
                    }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getTheBestBengkelMotor(): LiveData<ResultState<List<DataItem>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getAllBengkel(token)
                val filteredResponse =
                    response.data.filter { bengkel ->
                        bengkel.spesialisasiBengkel == "Bengkel Motor" && bengkel.rating.firstOrNull()?.averageRating?.let {
                            it is Number && it.toDouble() >= 4.5
                        } ?: false
                    }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getOfficialBengkelMotor(): LiveData<ResultState<List<DataItem>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getAllBengkel(token)
                val filteredResponse = response.data.filter { bengkel ->
                    bengkel.spesialisasiBengkel == "Bengkel Motor" && bengkel.jenisBengkel == "Bengkel Resmi"
                }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getPublicBengkelMotor(): LiveData<ResultState<List<DataItem>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getAllBengkel(token)
                val filteredResponse = response.data.filter { bengkel ->
                    bengkel.spesialisasiBengkel == "Bengkel Motor" && bengkel.jenisBengkel == "Bengkel Umum"
                }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getAllBengkelMotor(): LiveData<ResultState<List<DataItem>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getAllBengkel(token)
                val filteredResponse =
                    response.data.filter { bengkel ->
                        bengkel.spesialisasiBengkel == "Bengkel Motor"
                    }
                emit(ResultState.Success(filteredResponse))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getDetailBengkel(id: Int): LiveData<ResultState<DetailBengkel>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getDetailBengkel(token, id)
                emit(ResultState.Success(response.data))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getLayananBengkel(id: Int): LiveData<ResultState<List<ServicesItem>>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.getDetailBengkel(token, id)
                val services = response.data.services
                emit(ResultState.Success(services))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun orderBengkelService(
        bengkelId: Int,
        serviceId: List<Int>,
        additionalInfo: String,
        fullName: String,
        complaint: String
    ): LiveData<ResultState<OrderBengkelServiceResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = bengkelApi.orderBengkelService(
                    token = token,
                    bengkelId = bengkelId,
                    serviceId = serviceId,
                    additionalInfo = additionalInfo,
                    fullName = fullName,
                    complaint = complaint
                )
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }
}