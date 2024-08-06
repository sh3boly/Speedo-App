package com.example.speedoapp.model

import com.example.speedoapp.constants.Constants.FAVOURITE_RECIPIENT_ACCOUNT_NUMBER
import com.example.speedoapp.constants.Constants.FAVOURITE_RECIPIENT_NAME
import com.google.gson.annotations.SerializedName

data class FavouriteRequest(
    @SerializedName(FAVOURITE_RECIPIENT_NAME)
    val recipientName: String,
    @SerializedName(FAVOURITE_RECIPIENT_ACCOUNT_NUMBER)
    val recipientAccountNumber: String
)
