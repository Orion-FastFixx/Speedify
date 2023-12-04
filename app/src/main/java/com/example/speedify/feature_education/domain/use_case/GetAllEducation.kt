package com.example.speedify.feature_education.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.feature_education.domain.entity.EducationEntity
import com.example.speedify.feature_education.domain.interface_repository.EducationRepository
import com.example.speedify.utils.ResultState

class GetAllEducation(private val repository: EducationRepository) {
    suspend operator fun invoke(): LiveData<ResultState<List<EducationEntity>>> {
        return repository.getAllEducation()
    }
}