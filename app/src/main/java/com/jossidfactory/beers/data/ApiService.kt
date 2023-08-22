package com.jossidfactory.beers.data

import com.jossidfactory.beers.data.dto.BeerDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("beers")
    suspend fun getBeers(): List<BeerDto>

    @GET("beers/{beerId}")
    suspend fun getBeerById(@Path("beerId") beerId: String): List<BeerDto>
}