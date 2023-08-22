package com.jossidfactory.beers.domain.usecase

import com.jossidfactory.beers.data.BeersRepository
import com.jossidfactory.beers.domain.model.BeerModel

class GetBeersListUseCase (
    private val beersRepository: BeersRepository
) {
    suspend fun invoke(): List<BeerModel> = beersRepository.getBeers()
}