package com.example.speedify.feature_activity.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.speedify.feature_activity.data.datasource.PesananDataSource
import com.example.speedify.feature_activity.domain.entity.PesananEntity
import com.example.speedify.feature_activity.domain.entity.Status
import com.example.speedify.feature_activity.domain.interface_repositoty.PesananRepo

import com.example.speedify.utils.ResultState

class PesananRepoImpl private constructor() : PesananRepo {

    override suspend fun getAllPesanan(): LiveData<ResultState<List<PesananEntity>>> =
        liveData {
            try {
                val response = PesananDataSource.getAllPesanan()
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getPesananProses(): LiveData<ResultState<List<PesananEntity>>> =
        liveData {
            try{
                val response = PesananDataSource.getAllPesanan()
                val filteredResponse =
                    response.filter{ it.status == Status.Proses }
                emit(ResultState.Success(filteredResponse))
            }catch (e: Exception){
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getPesananSelesai(): LiveData<ResultState<List<PesananEntity>>> =
        liveData {
            try{
                val response = PesananDataSource.getAllPesanan()
                val filteredResponse =
                    response.filter{ it.status == Status.Selesai }
                emit(ResultState.Success(filteredResponse))
            }catch (e: Exception){
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun getPesananBatal(): LiveData<ResultState<List<PesananEntity>>> =
        liveData {
            try{
                val response = PesananDataSource.getAllPesanan()
                val filteredResponse =
                    response.filter{ it.status == Status.Batal }
                emit(ResultState.Success(filteredResponse))
            }catch (e: Exception){
                emit(ResultState.Error(e.message.toString()))
            }
        }


    companion object {
        @Volatile
        private var instance: PesananRepoImpl? = null

        fun getInstance(): PesananRepoImpl =
            instance ?: synchronized(this) {
                instance ?: PesananRepoImpl().apply {
                    instance = this
                }
            }
    }
}