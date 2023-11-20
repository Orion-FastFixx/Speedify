package com.example.speedify.feature_consultation.data.datasource

import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.feature_consultation.domain.entity.dummyMontir

interface LocalData {
    fun getAllMontir(): List<MontirEntity>?
}

object MontirDataSource : LocalData {
    override fun getAllMontir(): List<MontirEntity> {
        return dummyMontir
    }
}