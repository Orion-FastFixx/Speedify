package com.example.speedify.feature_consultation.presentation.data.dataSource

import com.example.speedify.feature_consultation.presentation.domain.entity.MontirEntity
import com.example.speedify.feature_consultation.presentation.domain.entity.dummyMontir

interface LocalData {
    fun getAllMontir(): List<MontirEntity>?
}

object MontirDataSource : LocalData {
    override fun getAllMontir(): List<MontirEntity>? {
        return dummyMontir
    }
}