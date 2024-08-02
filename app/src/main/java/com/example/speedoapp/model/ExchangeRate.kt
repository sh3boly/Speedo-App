package com.example.speedoapp.model

import com.example.speedoapp.constants.Constants.AMOUNT
import com.example.speedoapp.constants.Constants.COUNTRY_CODE
import com.example.speedoapp.constants.Constants.COUNTRY_EXCHANGE_RATE
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ExchangeRate(
    @SerializedName(AMOUNT)
    val amount: String,
    @SerializedName(COUNTRY_CODE)
    val currency: String,
    @SerializedName(COUNTRY_EXCHANGE_RATE)
    val exchangeRate: String
)
