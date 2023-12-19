package com.example.speedify.di

import android.content.Context
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.core.data.remote.ApiConfig
import com.example.speedify.feature_activity.data.repository.PesananRepoImpl
import com.example.speedify.feature_activity.domain.interface_repositoty.PesananRepo
import com.example.speedify.feature_activity.domain.use_case.GetAllPesanan
import com.example.speedify.feature_activity.domain.use_case.GetPesananBatal
import com.example.speedify.feature_activity.domain.use_case.GetPesananProses
import com.example.speedify.feature_activity.domain.use_case.GetPesananSelesai
import com.example.speedify.feature_activity.domain.use_case.PesananUseCase
import com.example.speedify.feature_authentication.data.remote.AuthApi
import com.example.speedify.feature_authentication.data.repository.AuthRepositoryImpl
import com.example.speedify.feature_authentication.domain.interface_repository.AuthRepository
import com.example.speedify.feature_authentication.domain.use_case.GetCurrentUser
import com.example.speedify.feature_authentication.domain.use_case.SignIn
import com.example.speedify.feature_authentication.domain.use_case.SignOut
import com.example.speedify.feature_authentication.domain.use_case.SignUp
import com.example.speedify.feature_authentication.domain.use_case.UseCasesAuth
import com.example.speedify.feature_bengkel.data.remote.BengkelApi
import com.example.speedify.feature_bengkel.data.repository.BengkelRepositoryImpl
import com.example.speedify.feature_bengkel.domain.interface_repository.BengkelRepository
import com.example.speedify.feature_bengkel.domain.use_case.GetAllBengkelMobil
import com.example.speedify.feature_bengkel.domain.use_case.GetAllBengkelMotor
import com.example.speedify.feature_bengkel.domain.use_case.GetAllPromotion
import com.example.speedify.feature_bengkel.domain.use_case.GetBengkelMobilWithHighReview
import com.example.speedify.feature_bengkel.domain.use_case.GetBengkelMotorWithHighReview
import com.example.speedify.feature_bengkel.domain.use_case.GetDetailBengkel
import com.example.speedify.feature_bengkel.domain.use_case.GetLayananBengkel
import com.example.speedify.feature_bengkel.domain.use_case.GetOfficialBengkelMobil
import com.example.speedify.feature_bengkel.domain.use_case.GetOfficialBengkelMotor
import com.example.speedify.feature_bengkel.domain.use_case.GetPublicBengkelMobil
import com.example.speedify.feature_bengkel.domain.use_case.GetPublicBengkelMotor
import com.example.speedify.feature_bengkel.domain.use_case.GetTheBestBengkelMobil
import com.example.speedify.feature_bengkel.domain.use_case.GetTheBestBengkelMotor
import com.example.speedify.feature_bengkel.domain.use_case.OrderBengkelService
import com.example.speedify.feature_bengkel.domain.use_case.PayOrderService
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import com.example.speedify.feature_consultation.data.remote.MontirApi
import com.example.speedify.feature_consultation.data.repository.MontirRepoImpl
import com.example.speedify.feature_consultation.domain.interface_repository.MontirRepo
import com.example.speedify.feature_consultation.domain.use_case.GetAllMontir
import com.example.speedify.feature_consultation.domain.use_case.MontirUseCase
import com.example.speedify.feature_consultation.domain.use_case.OrderMontirService
import com.example.speedify.feature_education.data.remote.EducationApi
import com.example.speedify.feature_education.data.repository.EducationRepositoryImpl
import com.example.speedify.feature_education.domain.interface_repository.EducationRepository
import com.example.speedify.feature_education.domain.use_case.GetAllEducation
import com.example.speedify.feature_education.domain.use_case.GetDetailEducation
import com.example.speedify.feature_education.domain.use_case.GetEducationExterior
import com.example.speedify.feature_education.domain.use_case.GetEducationInterior
import com.example.speedify.feature_education.domain.use_case.GetEducationMesin
import com.example.speedify.feature_education.domain.use_case.GetEducationTips
import com.example.speedify.feature_education.domain.use_case.UseCasesEducation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SpeedifyModule {

    @Provides
    fun provideMontirApi(
        @ApplicationContext context: Context,
        dataStore: UserDataStoreImpl
    ): MontirApi =
        ApiConfig.getApiService(context, dataStore)

    @Provides
    @Singleton
    fun provideMontirRepository(
        montirApi: MontirApi,
        dataStore: UserDataStoreImpl
    ): MontirRepo {
        return MontirRepoImpl(montirApi, dataStore)
    }


    @Provides
    @Singleton
    fun provideMontirUseCase(repository: MontirRepo): MontirUseCase {
        return MontirUseCase(
            getAllMontir = GetAllMontir(repository),
            orderMontirService = OrderMontirService(repository),
        )
    }

    @Provides
    fun provideBengkelApi(
        @ApplicationContext context: Context,
        dataStore: UserDataStoreImpl
    ): BengkelApi =
        ApiConfig.getApiService(context, dataStore)


    @Provides
    @Singleton
    fun provideBengkelRepository(
        bengkelApi: BengkelApi,
        dataStore: UserDataStoreImpl
    ): BengkelRepository {
        return BengkelRepositoryImpl(bengkelApi, dataStore)
    }

    @Provides
    @Singleton
    fun provideBengkelUseCases(repository: BengkelRepository): UseCasesBengkel {
        return UseCasesBengkel(
            getAllPromotion = GetAllPromotion(repository),
            getAllBengkelMobil = GetAllBengkelMobil(repository),
            getOfficialBengkelMobil = GetOfficialBengkelMobil(repository),
            getPublicBengkelMobil = GetPublicBengkelMobil(repository),
            getBengkelMobilWithHighReview = GetBengkelMobilWithHighReview(repository),
            getTheBestBengkelMobil = GetTheBestBengkelMobil(repository),
            getAllBengkelMotor = GetAllBengkelMotor(repository),
            getOfficialBengkelMotor = GetOfficialBengkelMotor(repository),
            getPublicBengkelMotor = GetPublicBengkelMotor(repository),
            getBengkelMotorWithHighReview = GetBengkelMotorWithHighReview(repository),
            getTheBestBengkelMotor = GetTheBestBengkelMotor(repository),
            getDetailBengkel = GetDetailBengkel(repository),
            getLayananBengkel = GetLayananBengkel(repository),
            orderBengkelService = OrderBengkelService(repository),
            payOrderService = PayOrderService(repository)
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
    fun provideEducationApi(
        @ApplicationContext context: Context,
        dataStore: UserDataStoreImpl
    ): EducationApi =
        ApiConfig.getApiService(context, dataStore)

    @Provides
    @Singleton
    fun provideEducationRepository(
        educationApi: EducationApi,
        dataStore: UserDataStoreImpl
    ): EducationRepository {
        return EducationRepositoryImpl(educationApi, dataStore)
    }

    @Provides
    @Singleton
    fun provideEducationUsesCases(repository: EducationRepository): UseCasesEducation {
        return UseCasesEducation(
            getAllEducation = GetAllEducation(repository),
            getEducationTips = GetEducationTips(repository),
            getEducationInterior = GetEducationInterior(repository),
            getEducationExterior = GetEducationExterior(repository),
            getEducationMesin = GetEducationMesin(repository),
            getDetailEducation = GetDetailEducation(repository)
        )
    }

    @Provides
    fun provideAuthApi(
        @ApplicationContext context: Context,
        dataStore: UserDataStoreImpl
    ): AuthApi =
        ApiConfig.getApiService(context, dataStore)

    @Provides
    @Singleton
    fun provideAuthRepository(
        authenticator: AuthApi,
        dataStore: UserDataStoreImpl
    ): AuthRepository {
        return AuthRepositoryImpl(authenticator, dataStore)
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(repository: AuthRepository): UseCasesAuth {
        return UseCasesAuth(
            signIn = SignIn(repository),
            signUp = SignUp(repository),
            getCurrentUser = GetCurrentUser(repository),
            signOut = SignOut(repository)
        )
    }
}