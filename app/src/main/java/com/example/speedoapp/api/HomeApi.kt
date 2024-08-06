package com.example.speedoapp.api

import com.example.speedoapp.model.BalanceResponse
import com.example.speedoapp.model.LogoutResponse
import com.example.speedoapp.model.TransactionRoot
import com.example.speedoapp.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeApi {
    @GET("api/balance")
    suspend fun getBalance(): Response<BalanceResponse>

//    @GET("api/transactions")
//    suspend fun getTransactions(): TransactionRoot
//
//    @GET("api/user")
//    suspend fun getUser(): User
//
    @POST("api/logout")
    suspend fun logout(): Response<LogoutResponse>

}