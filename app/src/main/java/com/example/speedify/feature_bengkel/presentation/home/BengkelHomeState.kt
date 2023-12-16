package com.example.speedify.feature_bengkel.presentation.home

import com.example.speedify.feature_bengkel.data.model.DataItem
import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity
import com.example.speedify.feature_bengkel.domain.entity.PromotionEntity
import com.example.speedify.feature_bengkel.domain.use_case.GetOfficialBengkelMobil

data class BengkelHomeState(
    val isLoading: Boolean = true,
    val error: String? = null,

    val promotion: List<PromotionEntity>? = null,
    val officialBengkelMobil: List<DataItem>? = null,
    val publicBengkelMobil: List<DataItem>? = null,
    val bengkelMobilwithHighReview: List<DataItem>? = null,
    val theBestBengkelMobil: List<DataItem>? = null,

    val officialBengkelMotor: List<DataItem>? = null,
    val publicBengkelMotor: List<DataItem>? = null,
    val bengkelMotorwithHighReview: List<DataItem>? = null,
    val theBestBengkelMotor: List<DataItem>? = null,
)