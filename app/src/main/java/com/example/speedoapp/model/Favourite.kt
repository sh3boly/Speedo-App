package com.example.speedoapp.model

import com.example.speedoapp.constants.Constants.FAVOURITE_RECIPIENT_ACCOUNT_NUMBER
import com.example.speedoapp.constants.Constants.FAVOURITE_RECIPIENT_ID
import com.example.speedoapp.constants.Constants.FAVOURITE_RECIPIENT_NAME
import com.google.gson.annotations.SerializedName

data class Favourite(
    @SerializedName(FAVOURITE_RECIPIENT_ID)
    val id: Int,
    @SerializedName(FAVOURITE_RECIPIENT_NAME)
    val recipientName: String,
    @SerializedName(FAVOURITE_RECIPIENT_ACCOUNT_NUMBER)
    val recipientAccountNumber: String
)