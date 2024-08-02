package com.example.speedoapp.model

data class LoginResponseTest(
    val token: String,
    )
data class RegisterResponseTest(
    val id: Int,
    val token: String,

)
sealed class LoginStatus {
    data class Success(val loginResponse: LoginResponseTest) : LoginStatus()
    data class Error(val message: String) : LoginStatus()
}
sealed class RegisterStatus {
    data class Success(val loginResponse: RegisterResponseTest) : RegisterStatus()
    data class Error(val message: String) : RegisterStatus()
}