package com.example.speedoapp.ui.notifications

import com.example.speedoapp.constants.Constants
import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName(Constants.TYPE)
    val type: String,
    @SerializedName(Constants.AMOUNT)
    val amount: String,
    @SerializedName(Constants.SENDER_NAME)
    val sender: String,
    @SerializedName(Constants.SENDER_ACCOUNT)
    val account:String,
    @SerializedName(Constants.TIME)
    val time: String
)