package com.jossidfactory.beers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jossidfactory.beers.model.Beer

@Composable
fun BeerDetail(beer: Beer) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(text = "Name :${beer.name}")
        Text(text = "Description: ${beer.description}")
        Text(text = "Graduation ${beer.abv}")
    }
}