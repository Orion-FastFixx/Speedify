package com.example.speedify.feature_education.presentation.education

import com.example.speedify.feature_education.domain.entity.EducationEntity

data class EducationState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val education: List<EducationEntity>? = null,
)
