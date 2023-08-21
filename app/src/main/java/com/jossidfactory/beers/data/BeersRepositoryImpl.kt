package com.jossidfactory.beers.data

import com.jossidfactory.beers.domain.model.Beer

class BeersRepositoryImpl(private val apiService: ApiService): BeersRepository {
    override suspend fun getBeers(): List<Beer> {
        return apiService.getBeers()
    }

    override suspend fun getBeerById(beerId: String): List<Beer> {
        return apiService.getBeerById(beerId)
    }
}