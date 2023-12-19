package com.example.speedify.feature_bengkel.presentation.review_bengkel.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_bengkel.domain.use_case.UseCasesBengkel
import com.example.speedify.feature_bengkel.presentation.review_bengkel.BengkelReviewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BengkelReviewViewModel @Inject constructor(
    private val useCases: UseCasesBengkel
) : ViewModel() {

    private val _bengkelReviewState = MutableStateFlow(BengkelReviewState())
    val bengkelReviewState = _bengkelReviewState.asStateFlow()


    fun getDetailRatingReviewBengkel(id: Int) {
        viewModelScope.launch {
            useCases.getDetailRatingReviewBengkel(id).asFlow().collect() {
                when (it) {
                    is ResultState.Loading -> _bengkelReviewState.value =
                        _bengkelReviewState.value.copy(
                            isLoading = true,
                            error = null,
                            bengkelRatingReview = null
                        )

                    is ResultState.Success -> _bengkelReviewState.value =
                        _bengkelReviewState.value.copy(
                            isLoading = false,
                            error = null,
                            bengkelRatingReview = it.data
                        )

                    is ResultState.Error -> _bengkelReviewState.value =
                        _bengkelReviewState.value.copy(
                            isLoading = false,
                            error = it.error,
                            bengkelRatingReview = null
                        )
                }
            }
        }
    }
}