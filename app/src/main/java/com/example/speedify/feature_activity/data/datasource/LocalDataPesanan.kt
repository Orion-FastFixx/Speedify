package com.example.speedify.feature_activity.data.datasource

import com.example.speedify.feature_activity.domain.entity.PesananEntity
import com.example.speedify.feature_activity.domain.entity.dummyPesanan
import com.example.speedify.feature_consultation.data.datasource.LocalData
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.feature_consultation.domain.entity.dummyMontir

interface LocalDataPesanan {
    fun getAllPesanan(): List<PesananEntity>
}

object PesananDataSource : LocalDataPesanan {
    override fun getAllPesanan(): List<PesananEntity> {
        return dummyPesanan
    }
}