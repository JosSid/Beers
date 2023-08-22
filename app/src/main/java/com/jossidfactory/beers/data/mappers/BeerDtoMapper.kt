package com.jossidfactory.beers.data.mappers

import com.jossidfactory.beers.data.dto.BeerDto
import com.jossidfactory.beers.domain.model.BeerModel

fun BeerDto.toBeerModel() = BeerModel(
    id = id ?: 0,
    name = name ?: "",
    description = description ?: "",
    imageUrl = imageUrl ?: "",
    abv = abv ?: 0.0
)