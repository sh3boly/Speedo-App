package com.example.speedoapp.model

import com.google.gson.annotations.SerializedName

data class BalanceResponse(
    @SerializedName("phoneNumber")
    val phoneNumber: Any?,
    @SerializedName("balance")
    val balance: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String
)
