package com.jossidfactory.beers.service

import com.jossidfactory.beers.model.Beer
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("beers")
    suspend fun getBeers(): List<Beer>
}