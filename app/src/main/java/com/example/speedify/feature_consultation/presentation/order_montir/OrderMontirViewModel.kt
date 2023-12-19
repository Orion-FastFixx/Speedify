package com.example.speedify.feature_consultation.presentation.order_montir

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import com.example.speedify.feature_consultation.domain.use_case.MontirUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderMontirViewModel @Inject constructor(
    private val useCases: MontirUseCase
) : ViewModel() {

    private val _orderMontirState = MutableStateFlow(OrderMontirState())
    val orderMontirState = _orderMontirState.asStateFlow()

    fun payOrderMontirService(orderId: Int, paymentMethodId: Int) {
        viewModelScope.launch {
            useCases.payOrderMontirService(orderId, paymentMethodId).asFlow().collect() { result ->
                when (result) {
                    is ResultState.Loading -> {
                        _orderMontirState.value = _orderMontirState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _orderMontirState.value = _orderMontirState.value.copy(
                            isLoading = false,
                            error = null,
                            payOrderMontirResponse = result.data
                        )
                    }

                    is ResultState.Error -> {
                        _orderMontirState.value = _orderMontirState.value.copy(
                            isLoading = false,
                            error = result.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}