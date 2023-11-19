package com.example.speedify.feature_bengkel.domain.entity

import androidx.annotation.DrawableRes
import com.example.speedify.R

data class PromotionEntity(
    val id: String,
    @DrawableRes val foto: Int,
)

val dummyPromotion = listOf(
    PromotionEntity("1", R.drawable.img_advertise_2),
    PromotionEntity("2", R.drawable.img_advertise_1),
    PromotionEntity("3", R.drawable.img_advertise_2)
)
