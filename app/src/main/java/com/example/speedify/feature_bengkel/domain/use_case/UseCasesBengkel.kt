package com.example.speedify.feature_bengkel.domain.use_case

data class UseCasesBengkel(
    val getAllPromotion: GetAllPromotion,
    val getAllBengkelMobil: GetAllBengkelMobil,
    val getNearestBengkelMobil: GetNearestBengkelMobil,
    val getTheBestBengkelMobil: GetTheBestBengkelMobil,
    val getAllBengkelMotor: GetAllBengkelMotor
)