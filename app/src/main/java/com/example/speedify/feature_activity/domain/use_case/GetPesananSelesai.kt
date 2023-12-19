package com.example.speedify.feature_activity.domain.use_case

import androidx.lifecycle.LiveData
import com.example.speedify.feature_activity.domain.entity.PesananEntity
import com.example.speedify.feature_activity.domain.interface_repositoty.PesananRepo
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_activity.data.model.OrderItem

class GetPesananSelesai(private val repository: PesananRepo) {
    suspend operator fun invoke(): LiveData<ResultState<List<OrderItem>>>{
        return repository.getPesananSelesai()
    }
}