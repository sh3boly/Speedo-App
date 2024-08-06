package com.example.speedoapp.api

import AuthInterceptor
import PreferencesManager
import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_URL =
        "http://basic-spring-app-env.eba-i6chqiqc.eu-north-1.elasticbeanstalk.com/"
    private const val FAV_URL =
        "http://bm-app-env.eba-yspz5pwf.eu-north-1.elasticbeanstalk.com/"


    val tokenManager = PreferencesManager
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

    private val favRetrofit = Retrofit
        .Builder()
        .baseUrl(FAV_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val authApi: AuthApi by lazy {
        authRetrofit.create(AuthApi::class.java)
    }

    val transferApi: TransferApiCallable by lazy {
        unauthRetrofit.create(TransferApiCallable::class.java)
    }

    val homeApi: HomeApi by lazy {
        try {
            unauthRetrofit.create(HomeApi::class.java)
        } catch (e: Exception) {
            throw e
        }
    }

    val favouriteApi: FavouriteApiCallable by lazy {
        favRetrofit.create(FavouriteApiCallable::class.java)
    }
}