package com.jossidfactory.beers.data.mappers

import com.jossidfactory.beers.data.local.model.BeerLocal
import com.jossidfactory.beers.domain.model.BeerModel

fun BeerLocal.toBeerModel() = BeerModel(
    id = id ?: 0,
    name = name ?: "",
    description = description ?: "",
    imageUrl = imageUrl ?: "",
    abv = abv ?: 0.0
)