package com.example.speedify.feature_consultation.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_consultation.data.model.DaftarItem
import com.example.speedify.feature_consultation.data.remote.MontirApi
import javax.inject.Inject

class MontirRepoImpl @Inject constructor(
    private val montirApi: MontirApi,
    private val dataStore: UserDataStoreImpl
) : MontirRepo {

    override suspend fun getAllMontir(): LiveData<ResultState<List<DaftarItem>>> =
        liveData {
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }
                val response = montirApi.getAllMontir(token)
                val daftarItems = response.data
                emit(ResultState.Success(daftarItems))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }
}