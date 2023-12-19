package com.example.speedify.feature_bengkel.domain.use_case

data class UseCasesBengkel(
    val getAllPromotion: GetAllPromotion,
    val getAllBengkelMobil: GetAllBengkelMobil,
    val getOfficialBengkelMobil: GetOfficialBengkelMobil,
    val getPublicBengkelMobil: GetPublicBengkelMobil,
    val getBengkelMobilWithHighReview: GetBengkelMobilWithHighReview,
    val getTheBestBengkelMobil: GetTheBestBengkelMobil,
    val getAllBengkelMotor: GetAllBengkelMotor,
    val getOfficialBengkelMotor: GetOfficialBengkelMotor,
    val getPublicBengkelMotor: GetPublicBengkelMotor,
    val getBengkelMotorWithHighReview: GetBengkelMotorWithHighReview,
    val getTheBestBengkelMotor: GetTheBestBengkelMotor,
    val getDetailBengkel: GetDetailBengkel,
    val getLayananBengkel: GetLayananBengkel,
    val orderBengkelService: OrderBengkelService,
    val payOrderService: PayOrderService
)