package com.example.speedoapp.model

import com.example.speedoapp.constants.Constants.RECIPIENT_ACCOUNT_NUMBER
import com.example.speedoapp.constants.Constants.RECIPIENT_NAME
import com.google.gson.annotations.SerializedName

data class Recipient(
    @SerializedName(RECIPIENT_NAME)
    val name: String,
    @SerializedName(RECIPIENT_ACCOUNT_NUMBER)
    val accountNumber: String
)
