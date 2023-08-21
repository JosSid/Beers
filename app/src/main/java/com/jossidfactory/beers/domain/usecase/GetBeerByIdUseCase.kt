package com.jossidfactory.beers.domain.usecase

import com.jossidfactory.beers.data.BeersRepository
import com.jossidfactory.beers.domain.model.Beer

class GetBeerByIdUseCase(
    private val beersRepository: BeersRepository
) {
    suspend fun invoke(beerId: String): List<Beer> = beersRepository.getBeerById(beerId)
}