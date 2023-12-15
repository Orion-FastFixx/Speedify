package com.example.speedify.feature_authentication.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.core.data.remote.ApiConfig
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_authentication.domain.use_case.UseCasesAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val useCases: UseCasesAuth,
    private val userDataStoreRepository: UserDataStoreImpl
) : ViewModel() {

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            useCases.signIn(email, password).asFlow().collect() { result ->
                when (result) {
                    is ResultState.Loading -> {
                        _signInState.value = _signInState.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }

                    is ResultState.Success -> {
                        // Save the token once the sign-in is successful
                        result.data.user.token.let { token ->
                            userDataStoreRepository.saveUserToken(token)
                            ApiConfig.TOKEN = token // Set the token for API calls
                        }

                        _signInState.value = _signInState.value.copy(
                            isLoading = false,
                            error = null,
                            user = result.data
                        )
                    }

                    is ResultState.Error -> {
                        _signInState.value = _signInState.value.copy(
                            isLoading = false,
                            error = result.error ?: "An error occurred"
                        )
                    }
                }
            }
        }
    }
}