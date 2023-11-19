package com.example.speedify.di

import com.example.speedify.feature_bengkel.data.repository.BengkelRepositoryImpl
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository
import com.example.speedify.feature_bengkel.domain.use_case.GetAllBengkelMobil
import com.example.speedify.feature_bengkel.domain.use_case.GetAllPromotion
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SpeedifyModule {

    @Provides
    @Singleton
    fun provideBengkelRepository(): BengkelRepository {
        return BengkelRepositoryImpl.getInstance()
    }

    @Provides
    @Singleton
    fun provideBengkelUseCases(repository: BengkelRepository): UseCasesBengkel {
        return UseCasesBengkel(
            getAllPromotion = GetAllPromotion(repository),
            getAllBengkelMobil = GetAllBengkelMobil(repository)
        )
    }
}