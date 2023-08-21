package com.jossidfactory.beers.data

import com.jossidfactory.beers.domain.model.Beer
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("beers")
    suspend fun getBeers(): List<Beer>

    @GET("beers/{beerId}")
    suspend fun getBeerById(@Path("beerId") beerId: String): List<Beer>
}