package com.example.speedify.feature_bengkel.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
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

                        getAllBengkelMobil()
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

    fun getAllBengkelMobil() {
        viewModelScope.launch {
            useCases.getAllBengkelMobil().asFlow().collect() {
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
                            bengkelMobil = it.data
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