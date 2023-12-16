package com.example.speedify.feature_consultation.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.speedify.feature_consultation.data.datasource.MontirDataSource
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo
import com.example.speedify.core.utils.ResultState

class MontirRepoImpl private constructor() : MontirRepo {

    override suspend fun getAllMontir(): LiveData<ResultState<List<MontirEntity>>> =
        liveData {
            try {
                val response = MontirDataSource.getAllMontir()
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getTheBestMontir(): LiveData<ResultState<List<MontirEntity>>> =
        liveData {
            try{
                val response = MontirDataSource.getAllMontir()
                val filteredResponse =
                    response.filter{it.jlhRating.toDouble() >= 4.5 }
                        .sortedByDescending { it.jlhRating.toDouble() }
                emit(ResultState.Success(filteredResponse))
            }catch (e: Exception){
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getTrustedMontir(): LiveData<ResultState<List<MontirEntity>>> =
        liveData {
            try{
                val response = MontirDataSource.getAllMontir()
                val filteredResponse =
                    response.filter{it.jlhCostumer.toInt() >= 50 }
                        .sortedByDescending { it.jlhCostumer.toInt() }
                emit(ResultState.Success(filteredResponse))
            }catch (e: Exception){
                emit(ResultState.Error(e.message.toString()))
            }
        }
    companion object {
        @Volatile
        private var instance: MontirRepoImpl? = null

        fun getInstance(): MontirRepoImpl =
            instance ?: synchronized(this) {
                instance ?: MontirRepoImpl().apply {
                    instance = this
                }
            }
    }
}