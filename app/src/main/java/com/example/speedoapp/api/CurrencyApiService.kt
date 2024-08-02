package com.example.speedoapp.api

import com.example.speedoapp.constants.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CurrencyApiService {
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val callable: CurrencyApiCallable by lazy {
        retrofit.create(CurrencyApiCallable::class.java)
    }
}