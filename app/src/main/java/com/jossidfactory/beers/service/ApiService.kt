package com.jossidfactory.beers.service

import com.jossidfactory.beers.model.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("beers")
    suspend fun getBeers(): List<Beer>

    @GET("beers/{id}")
    suspend fun getBeerById(@Path("id") id: String): List<Beer>
}