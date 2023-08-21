package com.jossidfactory.beers.data

import com.jossidfactory.beers.domain.model.Beer

interface BeersRepository {
    suspend fun getBeers(): List<Beer>

    suspend fun getBeerById(beerId: String): List<Beer>
}