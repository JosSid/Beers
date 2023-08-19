package com.jossidfactory.beers.screen.detail

import com.jossidfactory.beers.model.Beer

data class DetailState(
    val beer: Beer? = null,
    val isLoading: Boolean = false,
    val error: Boolean = false
)
