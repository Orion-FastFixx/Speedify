package com.example.speedify.feature_bengkel.data.datasource

import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity
import com.example.speedify.feature_bengkel.domain.entity.LayananEntity
import com.example.speedify.feature_bengkel.domain.entity.PromotionEntity
import com.example.speedify.feature_bengkel.domain.entity.dummyBengkel
import com.example.speedify.feature_bengkel.domain.entity.dummyLayanan
import com.example.speedify.feature_bengkel.domain.entity.dummyPromotion

interface LocalDataSource {
    fun getAllPromotion(): List<PromotionEntity>?
    // fun getAllBengkel(): List<BengkelEntity>?
    fun getAllLayanan(): List<LayananEntity>?
}

object BengkelDataSource : LocalDataSource {
    override fun getAllPromotion(): List<PromotionEntity> {
        return dummyPromotion
    }

    // override fun getAllBengkel(): List<BengkelEntity> {
    //     return dummyBengkel
    // }

    override fun getAllLayanan(): List<LayananEntity> {
        return dummyLayanan
    }
}