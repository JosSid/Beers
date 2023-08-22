package com.jossidfactory.beers.data

import com.jossidfactory.beers.data.dto.BeerDto
import com.jossidfactory.beers.data.local.BeerDao
import com.jossidfactory.beers.data.local.model.BeerLocal
import com.jossidfactory.beers.data.mappers.toBeerLocal
import com.jossidfactory.beers.data.mappers.toBeerModel
import com.jossidfactory.beers.domain.model.BeerModel

class BeersRepositoryImpl(private val apiService: ApiService, private val beerDao: BeerDao): BeersRepository {
    override suspend fun getBeers(): List<BeerModel> {
        var beers: List<BeerLocal> = beerDao.getBeers()

        return if(beers.isEmpty()) {
            var beersApi: List<BeerDto> = apiService.getBeers()

            beerDao.insertAllBeers(beersApi.map { it.toBeerLocal() })

            beersApi.map { it.toBeerModel() }
        } else {
            beers.map() { it.toBeerModel() }
        }
    }

    override suspend fun getBeerById(beerId: String): List<BeerModel> {
        var beer: List<BeerLocal> = beerDao.getBeerById(beerId)

        return if(beer.isEmpty()) {
            var beerApi: List<BeerDto> = apiService.getBeerById(beerId)

            beerDao.insertAllBeers(beerApi.map { it.toBeerLocal() })

            beerApi.map { it.toBeerModel() }
        } else {
            beer.map() { it.toBeerModel() }
        }
    }
}