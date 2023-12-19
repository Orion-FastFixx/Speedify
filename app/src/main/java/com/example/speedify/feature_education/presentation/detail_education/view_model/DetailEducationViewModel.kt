package com.example.speedify.feature_education.presentation.detail_education.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.DetailBengkelState
import com.example.speedify.feature_education.domain.use_case.UseCasesEducation
import com.example.speedify.feature_education.presentation.detail_education.DetailEducationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailEducationViewModel @Inject constructor(
    private val useCases: UseCasesEducation
) : ViewModel() {

    private val _detailEducationState = MutableStateFlow(DetailEducationState())
    val detailEducationState = _detailEducationState.asStateFlow()


    fun getDetailEducation(id: Int) {
        viewModelScope.launch {
            useCases.getDetailEducation(id).asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _detailEducationState.value = _detailEducationState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _detailEducationState.value = _detailEducationState.value.copy(
                            isLoading = false,
                            error = null,
                            detailEducation = it.data
                        )
                    }

                    is ResultState.Error -> {
                        _detailEducationState.value =_detailEducationState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }

}