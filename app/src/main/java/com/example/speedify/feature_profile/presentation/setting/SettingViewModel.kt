package com.example.speedify.feature_profile.presentation.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_authentication.data.model.LogoutResponse
import com.example.speedify.feature_authentication.domain.use_case.UseCasesAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val useCases: UseCasesAuth,
    private val userDataStoreRepository: UserDataStoreImpl
) : ViewModel() {
    private val _signOutState = MutableLiveData<ResultState<LogoutResponse>>()
    val signOutState: LiveData<ResultState<LogoutResponse>> = _signOutState

    fun signOut() {
        viewModelScope.launch {
            useCases.signOut().asFlow().collect() { result ->
                when (result) {
                    is ResultState.Loading -> {
                        _signOutState.value = ResultState.Loading
                    }

                    is ResultState.Success -> {
                        userDataStoreRepository.clearUserLogin()
                        _signOutState.value = ResultState.Success(result.data)
                    }

                    is ResultState.Error -> {
                        _signOutState.value = ResultState.Error(result.error)
                    }
                }
            }
        }
    }
}