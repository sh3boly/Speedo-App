package com.example.speedoapp.api

import AuthInterceptor
import TokenManager
import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_URL = "http://10.0.2.2:3000/"


    val tokenManager = TokenManager
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(tokenManager))
        .build()
    private val unauthRetrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    private val authRetrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val authApi: AuthApi by lazy {
        authRetrofit.create(AuthApi::class.java)
    }

    val transferApi: TransferApiCallable by lazy {
        unauthRetrofit.create(TransferApiCallable::class.java)
    }

    val homeApi: HomeApi by lazy {
        unauthRetrofit.create(HomeApi::class.java)
    }
}