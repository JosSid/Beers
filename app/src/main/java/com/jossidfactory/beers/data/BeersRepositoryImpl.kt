package com.jossidfactory.beers.data

import com.jossidfactory.beers.domain.model.Beer

class BeersRepositoryImpl(): BeersRepository {
    override suspend fun getBeers(): List<Beer> {
        TODO("Not yet implemented")
    }

    override suspend fun getBeerById(beerId: String): List<Beer> {
        TODO("Not yet implemented")
    }
}