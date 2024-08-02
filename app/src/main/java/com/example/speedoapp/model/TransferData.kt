package com.example.speedoapp.model

import com.example.speedoapp.constants.Constants.RECIPIENT
import com.example.speedoapp.constants.Constants.TRANSFER_FROM
import com.example.speedoapp.constants.Constants.TRANSFER_TO
import com.google.gson.annotations.SerializedName

data class TransferData(
    @SerializedName(TRANSFER_FROM)
    val from: ExchangeRate,
    @SerializedName(TRANSFER_TO)
    val to: ExchangeRate,
    @SerializedName(RECIPIENT)
    val recipient: Recipient
)