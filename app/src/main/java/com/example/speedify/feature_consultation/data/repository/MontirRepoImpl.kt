package com.example.speedify.feature_consultation.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_consultation.data.model.PayOrderMontirResponse
import com.example.speedify.feature_consultation.data.model.DaftarItem
import com.example.speedify.feature_consultation.data.model.OrderMontirServiceResponse
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

    override suspend fun orderMontirService(
        montirId: Int,
        serviceId: List<Int>,
    ): LiveData<ResultState<OrderMontirServiceResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = montirApi.orderMontirService(
                    token = token,
                    montirId = montirId,
                    serviceId = serviceId,
                )
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

    override suspend fun payOrder(
        orderId: Int,
        paymentMethodId: Int
    ): LiveData<ResultState<PayOrderMontirResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val userPreferences = dataStore.getUser()
                val token = userPreferences.token
                if (token.isNullOrEmpty()) {
                    emit(ResultState.Error("No token found"))
                    return@liveData
                }

                val response = montirApi.payOrder(
                    token = token,
                    orderId = orderId,
                    paymentMethodId = paymentMethodId
                )
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                emit(ResultState.Error(e.message.toString()))
            }
        }

}