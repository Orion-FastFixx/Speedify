package com.example.speedify.di
import com.example.speedify.feature_consultation.data.repository.MontirRepoImpl
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo
import com.example.speedify.feature_consultation.domain.use_case.GetAllMontir
import com.example.speedify.feature_consultation.domain.use_case.MontirUseCase
import com.example.speedify.feature_bengkel.data.repository.BengkelRepositoryImpl
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository
import com.example.speedify.feature_bengkel.domain.use_case.GetAllBengkelMobil
import com.example.speedify.feature_bengkel.domain.use_case.GetAllBengkelMotor
import com.example.speedify.feature_bengkel.domain.use_case.GetAllPromotion
import com.example.speedify.feature_bengkel.domain.use_case.GetNearestBengkelMobil
import com.example.speedify.feature_bengkel.domain.use_case.GetTheBestBengkelMobil
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
    fun provideMontirRepository(): MontirRepo {
        return MontirRepoImpl.getInstance()
    }

    @Provides
    @Singleton
    fun provideMontirUseCase(repository: MontirRepo): MontirUseCase {
        return MontirUseCase(
            getAllMontir = GetAllMontir(repository),
        )
    }

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
            getAllBengkelMobil = GetAllBengkelMobil(repository),
            getNearestBengkelMobil = GetNearestBengkelMobil(repository),
            getTheBestBengkelMobil = GetTheBestBengkelMobil(repository),
            getAllBengkelMotor = GetAllBengkelMotor(repository)
        )
    }
}