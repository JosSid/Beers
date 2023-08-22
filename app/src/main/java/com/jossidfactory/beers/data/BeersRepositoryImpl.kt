package com.jossidfactory.beers.data

import com.jossidfactory.beers.data.mappers.toBeerModel
import com.jossidfactory.beers.domain.model.BeerModel

class BeersRepositoryImpl(private val apiService: ApiService): BeersRepository {
    override suspend fun getBeers(): List<BeerModel> {
        return apiService.getBeers().map { it.toBeerModel() }
    }

    override suspend fun getBeerById(beerId: String): List<BeerModel> {
        return apiService.getBeerById(beerId).map { it.toBeerModel() }
    }
}