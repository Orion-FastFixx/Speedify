package com.example.speedify.feature_education.presentation.education.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.feature_education.domain.use_case.UseCasesEducation
import com.example.speedify.feature_education.presentation.education.EducationState
import com.example.speedify.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EducationViewModel @Inject constructor(private val useCases: UseCasesEducation) :
    ViewModel() {

    private val _educationState = MutableStateFlow(EducationState())
    val educationState = _educationState.asStateFlow()

    init {
        getAllEducation()
    }

    fun getAllEducation() {
        viewModelScope.launch {
            useCases.getAllEducation().asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> {
                        _educationState.value = _educationState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        _educationState.value = _educationState.value.copy(
                            isLoading = false,
                            error = null,
                            education = it.data
                        )
                    }

                    is ResultState.Error -> {
                        _educationState.value = _educationState.value.copy(
                            isLoading = false,
                            error = it.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}