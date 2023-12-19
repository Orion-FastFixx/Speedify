package com.example.speedify.feature_education.presentation.detail_education

import com.example.speedify.feature_education.data.model.DetailItem

data class DetailEducationState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val detailEducation: DetailItem? = null,
)
