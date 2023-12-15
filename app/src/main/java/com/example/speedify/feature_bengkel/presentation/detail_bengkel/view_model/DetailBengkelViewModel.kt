package com.example.speedify.feature_bengkel.presentation.detail_bengkel.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.DetailBengkelState
import com.example.speedify.core.utils.ResultState
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

    init {
        getAllLayanan()
    }

    fun getAllLayanan() {
        viewModelScope.launch {
            useCases.getAllLayanan().asFlow().collect() {
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
}