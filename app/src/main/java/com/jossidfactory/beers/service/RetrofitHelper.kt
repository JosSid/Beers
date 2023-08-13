package com.jossidfactory.beers.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper {
    private const val URL = "https://api.punkapi.com/v2/"

    private val retrofit = Retrofit
                            .Builder()
                            .baseUrl(URL) // Base URL de la API
                            .addConverterFactory(GsonConverterFactory.create()) // Conversor JSON
                            .build()

    fun getInstance():ApiService {
        return retrofit.create(ApiService::class.java)
    }
}