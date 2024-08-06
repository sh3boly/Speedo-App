package com.example.speedoapp.api

import com.example.speedoapp.constants.Constants.ADD_FAVOURITE_ENDPOINT
import com.example.speedoapp.constants.Constants.DELETE_FAVOURITE_ENDPOINT
import com.example.speedoapp.constants.Constants.GET_FAVOURITE_ENDPOINT
import com.example.speedoapp.constants.Constants.UPDATE_FAVOURITE_ENDPOINT
import com.example.speedoapp.model.Favourite
import com.example.speedoapp.model.FavouriteRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FavouriteApiCallable {
    @GET(GET_FAVOURITE_ENDPOINT)
    suspend fun getFavourites(): Response<List<Favourite>>

    @POST(ADD_FAVOURITE_ENDPOINT)
    suspend fun addFavourite(@Body favourite: FavouriteRequest): Response<Unit>

    @DELETE(DELETE_FAVOURITE_ENDPOINT)
    suspend fun deleteFavourite(@Path("id") id: String): Response<Unit>

    @PUT(UPDATE_FAVOURITE_ENDPOINT)
    suspend fun updateFavourite(
        @Path("id") id: String,
        @Body favourite: FavouriteRequest
    ): Response<Unit>
}