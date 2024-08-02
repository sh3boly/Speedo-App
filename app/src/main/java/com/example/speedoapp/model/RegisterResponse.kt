package com.example.speedoapp.model

data class RegisterResponse(
    val message: String
)
sealed class RegisterStatus {
    data class Success(val loginResponse: RegisterResponse) : RegisterStatus()
    data class Error(val message: String) : RegisterStatus()
}