package com.example.speedify.feature_education.presentation.education

import com.example.speedify.feature_education.data.model.ContentItem
import com.example.speedify.feature_education.domain.entity.EducationEntity

data class EducationState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val education: List<ContentItem>? = null,
    val educationTips: List<ContentItem>? = null,
    val educationInterior: List<ContentItem>? = null,
    val educationExterior: List<ContentItem>? = null,
    val educationMesin: List<ContentItem>? = null,
)
