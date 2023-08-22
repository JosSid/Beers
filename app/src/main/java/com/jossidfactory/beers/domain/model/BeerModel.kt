package com.jossidfactory.beers.domain.model

data class BeerModel(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val abv: Double
)