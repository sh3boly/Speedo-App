package com.example.speedoapp.api

import com.example.speedoapp.constants.Constants.BASE_URL
import com.example.speedoapp.model.UpdateUserRequest
import com.example.speedoapp.model.UpdateUserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UpdateUserService {
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}