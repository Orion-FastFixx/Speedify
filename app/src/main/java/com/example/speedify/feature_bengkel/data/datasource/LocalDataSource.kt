package com.example.speedify.feature_bengkel.data.datasource

import com.example.speedify.feature_bengkel.domain.entity.PromotionEntity
import com.example.speedify.feature_bengkel.domain.entity.dummyPromotion

interface LocalDataSource {
    fun getAllPromotion(): List<PromotionEntity>?
}

object BengkelDataSource : LocalDataSource {
    override fun getAllPromotion(): List<PromotionEntity> {
        return dummyPromotion
    }
}