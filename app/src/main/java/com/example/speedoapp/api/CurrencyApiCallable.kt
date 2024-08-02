package com.example.speedoapp.api

import com.example.speedoapp.constants.Constants.CURRENCIES_ENDPOINT
import com.example.speedoapp.model.Currency


import retrofit2.http.GET

interface CurrencyApiCallable {
    @GET(CURRENCIES_ENDPOINT)
    suspend fun getCurrencies(): List<Currency>
}