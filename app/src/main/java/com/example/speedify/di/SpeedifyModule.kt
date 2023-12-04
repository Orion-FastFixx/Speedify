package com.example.speedify.di

import com.example.speedify.feature_activity.data.repository.PesananRepoImpl
import com.example.speedify.feature_activity.domain.interface_repositoty.PesananRepo
import com.example.speedify.feature_activity.domain.use_case.GetAllPesanan
import com.example.speedify.feature_activity.domain.use_case.GetPesananBatal
import com.example.speedify.feature_activity.domain.use_case.GetPesananProses
import com.example.speedify.feature_activity.domain.use_case.GetPesananSelesai
import com.example.speedify.feature_activity.domain.use_case.PesananUseCase
import com.example.speedify.feature_bengkel.data.repository.BengkelRepositoryImpl
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository
import com.example.speedify.feature_bengkel.domain.use_case.GetAllBengkelMobil
import com.example.speedify.feature_bengkel.domain.use_case.GetAllBengkelMotor
import com.example.speedify.feature_bengkel.domain.use_case.GetAllLayanan
import com.example.speedify.feature_bengkel.domain.use_case.GetAllPromotion
import com.example.speedify.feature_bengkel.domain.use_case.GetNearestBengkelMobil
import com.example.speedify.feature_bengkel.domain.use_case.GetTheBestBengkelMobil
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import com.example.speedify.feature_consultation.data.repository.MontirRepoImpl
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo
import com.example.speedify.feature_consultation.domain.use_case.GetAllMontir
import com.example.speedify.feature_consultation.domain.use_case.GetTheBestMontir
import com.example.speedify.feature_consultation.domain.use_case.GetTrustedMontir
import com.example.speedify.feature_consultation.domain.use_case.MontirUseCase
import com.example.speedify.feature_education.data.repository.EducationRepositoryImpl
import com.example.speedify.feature_education.domain.interface_repository.EducationRepository
import com.example.speedify.feature_education.domain.use_case.GetAllEducation
import com.example.speedify.feature_education.domain.use_case.UseCasesEducation
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
            getTheBestMontir = GetTheBestMontir(repository),
            getTrustedMontir = GetTrustedMontir(repository)
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
            getAllBengkelMotor = GetAllBengkelMotor(repository),
            getAllLayanan = GetAllLayanan(repository)
        )
    }

    @Provides
    @Singleton
    fun provideMPesananRepository(): PesananRepo {
        return PesananRepoImpl.getInstance()
    }

    @Provides
    @Singleton
    fun providePesananUseCase(repository: PesananRepo): PesananUseCase {
        return PesananUseCase(
            getAllPesanan = GetAllPesanan(repository),
            getPesananProses = GetPesananProses(repository),
            getPesananSelesai = GetPesananSelesai(repository),
            getPesananBatal = GetPesananBatal(repository),
        )
    }

    @Provides
    @Singleton
    fun provideEducationRepository(): EducationRepository {
        return EducationRepositoryImpl.getInstance()
    }

    @Provides
    @Singleton
    fun provideEducationUsesCases(repository: EducationRepository): UseCasesEducation {
        return UseCasesEducation(
            getAllEducation = GetAllEducation(repository)
        )
    }


}