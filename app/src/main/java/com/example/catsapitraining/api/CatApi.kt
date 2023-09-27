package com.example.catsapitraining.api

import com.example.catsapitraining.data.Cat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatApi {
    @GET("cats")
    // TODO: add your own X-Api-Key, dont use mine !!
    @Headers("X-Api-Key: ")
    fun getAllData(
        @Query("playfulness") w: Int
    ): Call<List<Cat>>
}