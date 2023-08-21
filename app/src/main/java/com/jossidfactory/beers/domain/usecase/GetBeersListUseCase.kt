package com.jossidfactory.beers.domain.usecase

import com.jossidfactory.beers.data.ApiService
import com.jossidfactory.beers.domain.model.Beer

class GetBeersListUseCase (
    private val apiService: ApiService
) {
    suspend fun invoke(): List<Beer> = apiService.getBeers()
}