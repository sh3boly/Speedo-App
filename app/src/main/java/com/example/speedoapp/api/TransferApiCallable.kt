package com.example.speedoapp.api

import com.example.speedoapp.constants.Constants.TRANSFER_ENDPOINT
import com.example.speedoapp.model.TransferData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TransferApiCallable {
    @POST(TRANSFER_ENDPOINT)
    suspend fun postTransfer(@Body transferData: TransferData): Response<Unit>
}