package com.example.speedify.feature_education.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.feature_education.domain.entity.EducationEntity
import com.example.speedify.feature_education.domain.interface_repository.EducationRepository
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.data.model.DetailBengkel
import com.example.speedify.feature_education.data.model.ContentItem
import com.example.speedify.feature_education.data.model.DetailItem

class GetDetailEducation(private val repository: EducationRepository) {
    suspend operator fun invoke(id: Int): LiveData<ResultState<DetailItem>> {
        return repository.getDetailEducation(id)
    }
}