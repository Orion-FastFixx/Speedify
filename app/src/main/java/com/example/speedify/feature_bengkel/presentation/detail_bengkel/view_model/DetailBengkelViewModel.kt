package com.example.speedify.feature_bengkel.presentation.detail_bengkel.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.DetailBengkelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailBengkelViewModel @Inject constructor(
    private val useCases: UseCasesBengkel
) : ViewModel() {

    private val _detailBengkelState = MutableStateFlow(DetailBengkelState())
    val detailBengkelState = _detailBengkelState.asStateFlow()


    fun getDetailBengkel(id: Int) {
        viewModelScope.launch {
            useCases.getDetailBengkel(id).asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _detailBengkelState.value = _detailBengkelState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _detailBengkelState.value = _detailBengkelState.value.copy(
                            isLoading = false,
                            error = null,
                            detailBengkel = it.data
                        )
                    }

                    is ResultState.Error -> {
                        _detailBengkelState.value = _detailBengkelState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getLayananBengkel(id: Int) {
        viewModelScope.launch {
            useCases.getLayananBengkel(id).asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _detailBengkelState.value = _detailBengkelState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _detailBengkelState.value = _detailBengkelState.value.copy(
                            isLoading = false,
                            error = null,
                            layanan = it.data
                        )
                    }

                    is ResultState.Error -> {
                        _detailBengkelState.value = _detailBengkelState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun orderBengkelService(
        bengkelId: Int,
        serviceId: List<Int>,
        additionalInfo: String,
        fullName: String,
        complaint: String,
    ) {
        viewModelScope.launch {
            useCases.orderBengkelService(
                bengkelId,
                serviceId,
                additionalInfo,
                fullName,
                complaint
            ).asFlow().collect() { result ->
                when (result) {
                    is ResultState.Loading -> {
                        _detailBengkelState.value = _detailBengkelState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _detailBengkelState.value = _detailBengkelState.value.copy(
                            isLoading = false,
                            error = null,
                            orderBengkelService = result.data
                        )
                    }

                    is ResultState.Error -> {
                        _detailBengkelState.value = _detailBengkelState.value.copy(
                            isLoading = false,
                            error = result.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}