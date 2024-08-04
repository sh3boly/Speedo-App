package com.example.speedoapp.api


import com.example.speedoapp.model.LoginRequest
import com.example.speedoapp.model.LoginResponse
import com.example.speedoapp.model.RegisterRequest
import com.example.speedoapp.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("api/register")
    suspend fun register(
        @Body request: RegisterRequest
    ) : Response<RegisterResponse>
}