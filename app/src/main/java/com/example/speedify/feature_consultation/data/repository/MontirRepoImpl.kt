package com.example.speedify.feature_consultation.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.speedify.feature_consultation.data.datasource.MontirDataSource
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo
import com.example.speedify.utils.ResultState

class MontirRepoImpl private constructor() : MontirRepo {

    override suspend fun getAllMontir(): LiveData<ResultState<List<MontirEntity>>> =
        liveData {
            try {
                val response = MontirDataSource.getAllMontir() ?: emptyList()
                emit(ResultState.Success(response))
            } catch (e: Exception) {
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