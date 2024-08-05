package com.example.speedoapp.model

import com.example.speedoapp.constants.Constants.COUNTRY_CODE
import com.example.speedoapp.constants.Constants.COUNTRY_EXCHANGE_RATE
import com.example.speedoapp.constants.Constants.COUNTRY_FLAG_IMAGE
import com.example.speedoapp.constants.Constants.COUNTRY_NAME
import com.google.gson.annotations.SerializedName


data class Currency(
    @SerializedName(COUNTRY_CODE)
    val code: String,
    @SerializedName(COUNTRY_NAME)
    val name: String,
    @SerializedName(COUNTRY_EXCHANGE_RATE)
    val exchangeRate: String,
    @SerializedName(COUNTRY_FLAG_IMAGE)
    val imageUrl: String
)
