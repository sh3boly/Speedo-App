package com.example.speedoapp.api

import com.example.speedoapp.model.Balance
import com.example.speedoapp.model.TransactionRoot
import com.example.speedoapp.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeApi {
    @GET("api/balance")
    suspend fun getBalance(): Balance

    @GET("api/transactions")
    suspend fun getTransactions(): TransactionRoot

    @GET("api/user")
    suspend fun getUser(): User

    @POST("api/logout")
    suspend fun logout(): Response<Unit>

}