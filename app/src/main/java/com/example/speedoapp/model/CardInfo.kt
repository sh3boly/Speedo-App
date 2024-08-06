package com.example.speedoapp.model

import com.example.speedoapp.constants.Constants.CARD_HOLDER
import com.example.speedoapp.constants.Constants.CARD_NO
import com.example.speedoapp.constants.Constants.CVV
import com.example.speedoapp.constants.Constants.EXPIRY_DATE
import com.google.gson.annotations.SerializedName

data class CardInfo(
    @SerializedName(CARD_HOLDER)
    val cardHolder:String,
    @SerializedName(CARD_NO)
    val cardNo: String,
    @SerializedName(EXPIRY_DATE)
    val expiryDate: String,
    @SerializedName(CVV)
    val cvv:String
)