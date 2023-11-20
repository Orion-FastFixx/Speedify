package com.example.speedify.di
import com.example.speedify.feature_consultation.presentation.data.repo.MontirRepoImpl
import com.example.speedify.feature_consultation.presentation.domain.interface_repo.MontirRepo
import com.example.speedify.feature_consultation.presentation.domain.use_case.GetAllMontir
import com.example.speedify.feature_consultation.presentation.domain.use_case.MontirUseCase
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
}