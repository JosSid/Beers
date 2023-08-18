package com.jossidfactory.beers.screen.home

import com.jossidfactory.beers.model.Beer

data class HomeState(
    val isLoading: Boolean = false,
    val searchValue: String = "",
    val filteredBeers: List<Beer> = listOf(),
    val error: Boolean = false
)
