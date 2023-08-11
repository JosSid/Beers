package com.jossidfactory.beers

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jossidfactory.beers.model.Beer

@Composable
fun BeerDetail(beer: Beer) {
    Column {
        Text(text = "Name :${beer.name}")
        Text(text = "Description: ${beer.description}")
        Text(text = "Graduation ${beer.abv}")
    }
}