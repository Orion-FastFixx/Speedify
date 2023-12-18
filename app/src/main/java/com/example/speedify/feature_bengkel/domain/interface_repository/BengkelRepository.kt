package com.example.speedify.feature_bengkel.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.model.DataItem
import com.example.speedify.feature_bengkel.data.model.DetailBengkel
import com.example.speedify.feature_bengkel.data.model.OrderBengkelService
import com.example.speedify.feature_bengkel.data.model.OrderBengkelServiceResponse
import com.example.speedify.feature_bengkel.data.model.ServicesItem
import com.example.speedify.feature_bengkel.domain.entity.PromotionEntity

interface BengkelRepository {

    suspend fun getALlPromotion(): LiveData<ResultState<List<PromotionEntity>>>
    suspend fun getAllBengkelMobil(): LiveData<ResultState<List<DataItem>>>
    suspend fun getTheBestBengkelMobil(): LiveData<ResultState<List<DataItem>>>
    suspend fun getOfficialBengkelMobil(): LiveData<ResultState<List<DataItem>>>
    suspend fun getPublicBengkelMobil(): LiveData<ResultState<List<DataItem>>>
    suspend fun getBengkelMobilWithHighReview(): LiveData<ResultState<List<DataItem>>>
    suspend fun getBengkelMotorWithHighReview(): LiveData<ResultState<List<DataItem>>>
    suspend fun getTheBestBengkelMotor(): LiveData<ResultState<List<DataItem>>>
    suspend fun getOfficialBengkelMotor(): LiveData<ResultState<List<DataItem>>>
    suspend fun getPublicBengkelMotor(): LiveData<ResultState<List<DataItem>>>
    suspend fun getAllBengkelMotor(): LiveData<ResultState<List<DataItem>>>
    suspend fun getDetailBengkel(id: Int): LiveData<ResultState<DetailBengkel>>
    suspend fun getLayananBengkel(id: Int): LiveData<ResultState<List<ServicesItem>>>
    suspend fun orderBengkelService(
        bengkelId: Int,
        serviceId: List<Int>,
        additionalInfo: String,
        fullName: String,
        complaint: String,
    ): LiveData<ResultState<OrderBengkelServiceResponse>>
}