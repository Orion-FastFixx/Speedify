package com.example.speedify.feature_bengkel.presentation.review_bengkel

import com.example.speedify.feature_bengkel.data.model.ReviewAllUser

data class BengkelReviewState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val bengkelRatingReview: List<ReviewAllUser>? = null,
)
