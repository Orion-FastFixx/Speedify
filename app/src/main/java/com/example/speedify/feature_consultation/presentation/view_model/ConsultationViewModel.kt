package com.example.speedify.feature_consultation.presentation.view_model


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.feature_consultation.domain.use_case.MontirUseCase
import com.example.speedify.feature_consultation.presentation.MontirState
import com.example.speedify.core.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ConsultationViewModel @Inject constructor(
    private val useCases: MontirUseCase
) : ViewModel() {

    private val _montirState = MutableStateFlow(MontirState())
    val montirState = _montirState.asStateFlow()

    init {
        getAllMontir()
    }

    fun getAllMontir() {
        viewModelScope.launch {
            useCases.getAllMontir().asFlow().collect {
                when (it) {
                    is ResultState.Loading -> {
                        _montirState.value = _montirState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _montirState.value = _montirState.value.copy(
                            isLoading = false,
                            error = null,
                            montir = it.data
                        )
                        
                    }

                    is ResultState.Error -> {
                        _montirState.value = _montirState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}
