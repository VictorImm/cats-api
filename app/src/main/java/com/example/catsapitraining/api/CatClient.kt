package com.example.catsapitraining.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CatClient {
    private const val BASE_URL = "https://api.api-ninjas.com/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: CatApi by lazy {
        retrofit.create(CatApi::class.java)
    }
}