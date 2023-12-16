package com.example.speedify.feature_bengkel.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.model.DataItem
import com.example.speedify.feature_bengkel.data.model.DetailBengkel
import com.example.speedify.feature_bengkel.domain.entity.LayananEntity
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
    suspend fun getAllLayanan(): LiveData<ResultState<List<LayananEntity>>>
    // suspend fun getDetailBengkel(id: Int): LiveData<ResultState<DetailBengkel>>
}