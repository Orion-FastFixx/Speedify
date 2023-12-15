package com.example.speedify.feature_authentication.presentation.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.speedify.core.utils.ResultState
import com.example.speedify.feature_authentication.domain.use_case.UseCasesAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCases: UseCasesAuth,
) : ViewModel() {

    private val _signUpState = MutableStateFlow(SignUpState())
    var signUpState = _signUpState.asStateFlow()

    fun signUp(email: String, password: String, username: String) = viewModelScope.launch {
        val pengendaraRole = 2

        useCases.signUp(
            email = email,
            password = password,
            username = username,
            roleId = pengendaraRole
        ).asFlow().collect() { result ->
            when (result) {
                is ResultState.Loading -> {
                    _signUpState.value = _signUpState.value.copy(
                        isLoading = true,
                        error = null
                    )
                }

                is ResultState.Success -> {
                    _signUpState.value = _signUpState.value.copy(
                        isLoading = false,
                        error = null,
                        user = result.data
                    )
                }

                is ResultState.Error -> {
                    _signUpState.value = _signUpState.value.copy(
                        isLoading = false,
                        error = result.error ?: "An error occurred"
                    )
                }
            }
        }
    }
}