package com.example.speedify.feature_education.data.datasource

import com.example.speedify.feature_education.domain.entity.EducationEntity
import com.example.speedify.feature_education.domain.entity.dummyEducation

interface LocalDataSource {
    fun getAllEducation(): List<EducationEntity>?
}

object EducationDataSource : LocalDataSource {
    override fun getAllEducation(): List<EducationEntity> {
        return dummyEducation
    }
}