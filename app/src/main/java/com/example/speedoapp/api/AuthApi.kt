package com.example.speedoapp.api


import com.example.speedoapp.model.LoginRequest
import com.example.speedoapp.model.LoginResponseTest
import com.example.speedoapp.model.RegisterRequest
import com.example.speedoapp.model.RegisterResponse
import com.example.speedoapp.model.RegisterResponseTest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponseTest>

    @POST("api/register")
    suspend fun register(
        @Body request: LoginRequest
    ) : Response<RegisterResponseTest>
}