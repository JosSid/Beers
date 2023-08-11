package com.jossidfactory.beers.model

data class Beer(
    val id: Int,
    val name: String,
    val description: String,
    val image_url: String,
    val abv: Double
)
