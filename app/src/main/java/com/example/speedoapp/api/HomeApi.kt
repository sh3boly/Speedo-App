package com.example.speedoapp.api

import com.example.speedoapp.model.TransactionRoot
import com.example.speedoapp.model.User
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {
    @GET("api/balance")
    suspend fun getBalance(): Response<Float>

    @GET("api/transactions")
    suspend fun getTransactions(): TransactionRoot

    @GET("api/user")
    suspend fun getUser(): User

}