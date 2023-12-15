package com.example.speedify.feature_bengkel.presentation.bengkel_motor.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import com.example.speedify.feature_bengkel.presentation.bengkel_motor.BengkelMotorState
import com.example.speedify.core.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BengkelMotorViewModel @Inject constructor(
    private val useCases: UseCasesBengkel
) : ViewModel() {

    private val _bengkelMotorState = MutableStateFlow(BengkelMotorState())
    val bengkelMotorState = _bengkelMotorState.asStateFlow()

    init {
        getAllBengkelMotor()
    }

    fun getAllBengkelMotor() {
        viewModelScope.launch {
            useCases.getAllBengkelMotor().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _bengkelMotorState.value = _bengkelMotorState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _bengkelMotorState.value = _bengkelMotorState.value.copy(
                            isLoading = false,
                            error = null,
                            bengkelMotor = it.data
                        )
                    }

                    is ResultState.Error -> {
                        _bengkelMotorState.value = _bengkelMotorState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}