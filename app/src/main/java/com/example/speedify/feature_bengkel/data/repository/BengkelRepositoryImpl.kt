package com.example.speedify.feature_bengkel.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.speedify.feature_bengkel.data.datasource.BengkelDataSource
import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity
import com.example.speedify.feature_bengkel.domain.entity.PromotionEntity
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository
import com.example.speedify.utils.ResultState

class BengkelRepositoryImpl private constructor() : BengkelRepository {
    override suspend fun getALlPromotion(): LiveData<ResultState<List<PromotionEntity>>> =
        liveData {
            try {
                val response = BengkelDataSource.getAllPromotion()
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getAllBengkelMobil(): LiveData<ResultState<List<BengkelEntity>>> =
        liveData {
            try {
                val response = BengkelDataSource.getAllBengkelMobil()
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    companion object {
        @Volatile
        private var instance: BengkelRepositoryImpl? = null

        fun getInstance(): BengkelRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: BengkelRepositoryImpl().apply {
                    instance = this
                }
            }
    }
}