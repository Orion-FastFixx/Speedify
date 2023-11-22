package com.example.speedify.feature_bengkel.presentation.bengkel_mobil.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import com.example.speedify.feature_bengkel.presentation.bengkel_mobil.BengkelMobilState
import com.example.speedify.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BengkelMobilViewModel @Inject constructor(
    private val useCases: UseCasesBengkel
) : ViewModel() {

    private val _bengkelMobilState = MutableStateFlow(BengkelMobilState())
    val bengkelMobilState = _bengkelMobilState.asStateFlow()

    init {
        getAllBengkelMobil()
    }

    fun getAllBengkelMobil() {
        viewModelScope.launch {
            useCases.getAllBengkelMobil().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _bengkelMobilState.value = _bengkelMobilState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _bengkelMobilState.value = _bengkelMobilState.value.copy(
                            isLoading = false,
                            error = null,
                            bengkelMobil = it.data
                        )
                    }

                    is ResultState.Error -> {
                        _bengkelMobilState.value = _bengkelMobilState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}