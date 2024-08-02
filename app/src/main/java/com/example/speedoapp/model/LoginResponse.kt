package com.example.speedoapp.model

data class LoginResponse(
    val token: String,
    val tokenType: String,
    val message: String,
    val status: String,

)