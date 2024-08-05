package com.example.speedoapp.model

data class BalanceResponse(
    val phoneNumber: String?,
    val balance: Int,
    val name: String,
    val username: String
)
