package com.jossidfactory.beers.screen.home

import com.jossidfactory.beers.domain.model.BeerModel

data class HomeState(
    val isLoading: Boolean = false,
    val searchValue: String = "",
    val filteredBeerDtos: List<BeerModel> = listOf(),
    val error: String = ""
)
