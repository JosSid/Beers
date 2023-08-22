package com.jossidfactory.beers.domain.usecase

import com.jossidfactory.beers.data.BeersRepository
import com.jossidfactory.beers.domain.model.BeerModel

class GetBeerByIdUseCase(
    private val beersRepository: BeersRepository
) {
    suspend fun invoke(beerId: String): List<BeerModel> = beersRepository.getBeerById(beerId)
}