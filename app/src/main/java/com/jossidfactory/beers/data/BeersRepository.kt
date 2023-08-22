package com.jossidfactory.beers.data

import com.jossidfactory.beers.domain.model.BeerModel

interface BeersRepository {
    suspend fun getBeers(): List<BeerModel>

    suspend fun getBeerById(beerId: String): List<BeerModel>
}