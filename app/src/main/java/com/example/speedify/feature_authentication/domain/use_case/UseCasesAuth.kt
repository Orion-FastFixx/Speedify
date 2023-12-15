package com.example.speedify.feature_authentication.domain.use_case

data class UseCasesAuth(
    val signIn: SignIn,
    val signUp: SignUp,
    val getCurrentUser: GetCurrentUser
)
