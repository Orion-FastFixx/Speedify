package com.example.speedify.feature_bengkel.presentation.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import com.example.speedify.feature_bengkel.presentation.home.BengkelHomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BengkelHomeViewModel @Inject constructor(
    private val useCases: UseCasesBengkel
) : ViewModel() {

    private val _bengkelHomeState = MutableStateFlow(BengkelHomeState())
    val bengkelState = _bengkelHomeState.asStateFlow()

    init {
        getAllPromotion()
    }


    fun getAllPromotion() {
        viewModelScope.launch {
            useCases.getAllPromotion().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = false,
                            error = null,
                            promotion = it.data
                        )

                        getTheBestBengkelMobil()

                    }

                    is ResultState.Error -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getBengkelMobilWithHighReview() {
        viewModelScope.launch {
            useCases.getBengkelMobilWithHighReview().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = false,
                            error = null,
                            bengkelMobilwithHighReview = it.data
                        )

                        getTheBestBengkelMobil()
                    }

                    is ResultState.Error -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
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
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = false,
                            error = null,
                            theBestBengkelMobil = it.data
                        )

                        getOfficialBengkelMobil()
                    }

                    is ResultState.Error -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getOfficialBengkelMobil() {
        viewModelScope.launch {
            useCases.getOfficialBengkelMobil().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = false,
                            error = null,
                            officialBengkelMobil = it.data
                        )

                        getPublicBengkelMobil()
                    }

                    is ResultState.Error -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

    fun getPublicBengkelMobil() {
        viewModelScope.launch {
            useCases.getPublicBengkelMobil().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = false,
                            error = null,
                            publicBengkelMobil = it.data
                        )
                    }

                    is ResultState.Error -> {
                        _bengkelHomeState.value = _bengkelHomeState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}