package com.example.speedoapp.model

data class LogoutResponse(val message: String)

sealed class LogoutStatus {
    data class Success(val logoutResponse: LogoutResponse) : LogoutStatus()
    data class Error(val message: String) : LogoutStatus()
}
