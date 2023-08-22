package com.jossidfactory.beers.screen.detail

import com.jossidfactory.beers.domain.model.BeerModel

data class DetailState(
    val beerDto: BeerModel? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
