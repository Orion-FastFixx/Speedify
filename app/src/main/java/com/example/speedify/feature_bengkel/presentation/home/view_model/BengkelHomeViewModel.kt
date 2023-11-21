package com.example.speedify.feature_bengkel.presentation.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import com.example.speedify.feature_bengkel.presentation.home.BengkelState
import com.example.speedify.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BengkelHomeViewModel @Inject constructor(
    private val useCases: UseCasesBengkel
) : ViewModel() {

    private val _bengkelState = MutableStateFlow(BengkelState())
    val bengkelState = _bengkelState.asStateFlow()

    init {
        getAllPromotion()
    }


    fun getAllPromotion() {
        viewModelScope.launch {
            useCases.getAllPromotion().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _bengkelState.value = _bengkelState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _bengkelState.value = _bengkelState.value.copy(
                            isLoading = false,
                            error = null,
                            promotion = it.data
                        )

                        getNearestBengkelMobil()

                    }

                    is ResultState.Error -> {
                        _bengkelState.value = _bengkelState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getNearestBengkelMobil() {
        viewModelScope.launch {
            useCases.getNearestBengkelMobil().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _bengkelState.value = _bengkelState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _bengkelState.value = _bengkelState.value.copy(
                            isLoading = false,
                            error = null,
                            nearestBengkelMobil = it.data
                        )

                        getTheBestBengkelMobil()
                    }

                    is ResultState.Error -> {
                        _bengkelState.value = _bengkelState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getTheBestBengkelMobil() {
        viewModelScope.launch {
            useCases.getTheBestBengkelMobil().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _bengkelState.value = _bengkelState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _bengkelState.value = _bengkelState.value.copy(
                            isLoading = false,
                            error = null,
                            theBestBengkelMobil = it.data
                        )
                    }

                    is ResultState.Error -> {
                        _bengkelState.value = _bengkelState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}