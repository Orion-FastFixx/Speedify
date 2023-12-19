package com.example.speedify.feature_bengkel.presentation.checkout_bengkel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutBengkelViewModel @Inject constructor(
    private val useCases: UseCasesBengkel
) : ViewModel() {

    private val _checkoutBengkelState = MutableStateFlow(CheckoutBengkelState())
    val checkoutBengkelState = _checkoutBengkelState.asStateFlow()

    fun payOrderService(orderId: Int, paymentMethodId: Int) {
        viewModelScope.launch {
            useCases.payOrderService(orderId, paymentMethodId).asFlow().collect() { result ->
                when (result) {
                    is ResultState.Loading -> {
                        _checkoutBengkelState.value = _checkoutBengkelState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _checkoutBengkelState.value = _checkoutBengkelState.value.copy(
                            isLoading = false,
                            error = null,
                            payOrderResponse = result.data
                        )
                    }

                    is ResultState.Error -> {
                        _checkoutBengkelState.value = _checkoutBengkelState.value.copy(
                            isLoading = false,
                            error = result.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}