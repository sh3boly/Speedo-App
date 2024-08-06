package com.example.speedoapp.api

import com.example.speedoapp.model.UpdateUserRequest
import com.example.speedoapp.model.UpdateUserResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface UpdateUserApi {
    @PUT("api/update/{email}")
    suspend fun updateUser(@Body updateUserRequest: UpdateUserRequest): UpdateUserResponse
}