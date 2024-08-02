package com.example.speedoapp.model

data class LoginResponse(
    val token: String,
    val tokenType: String,
    val message: String,
    val status: String,

)
sealed class LoginStatus {
    data class Success(val loginResponse: LoginResponse) : LoginStatus()
    data class Error(val message: String) : LoginStatus()
}