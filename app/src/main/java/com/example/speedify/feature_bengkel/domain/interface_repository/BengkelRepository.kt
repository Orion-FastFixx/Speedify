package com.example.speedify.feature_bengkel.domain.interface_repository

import androidx.lifecycle.LiveData
import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity
import com.example.speedify.feature_bengkel.domain.entity.PromotionEntity
import com.example.speedify.utils.ResultState

interface BengkelRepository {

    suspend fun getALlPromotion(): LiveData<ResultState<List<PromotionEntity>>>
    suspend fun getAllBengkelMobil(): LiveData<ResultState<List<BengkelEntity>>>
    suspend fun getNearestBengkelMobil(): LiveData<ResultState<List<BengkelEntity>>>
    suspend fun getTheBestBengkelMobil(): LiveData<ResultState<List<BengkelEntity>>>
    suspend fun getAllBengkelMotor(): LiveData<ResultState<List<BengkelEntity>>>
}