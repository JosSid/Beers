package com.jossidfactory.beers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.jossidfactory.beers.service.ApiService
import com.jossidfactory.beers.model.Beer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun ListBeers(value: String) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.punkapi.com/v2/") // Base URL de la API
        .addConverterFactory(GsonConverterFactory.create()) // Conversor JSON
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    var beers by remember { mutableStateOf(emptyList<Beer>()) }

    var filteredBeers by remember { mutableStateOf(beers) }

    LaunchedEffect(key1 = value) {
        try {
            beers = apiService.getBeers()
        } catch (e: Exception) {
            // Manejar errores de red aquí
        }
        if (value.isNotEmpty()) {
            filteredBeers = beers.filter { beer -> beer.name.lowercase().contains(value.lowercase())}
        } else {
            filteredBeers = beers
        }
    }
        Box{
            LazyColumn {
                items(filteredBeers) { beer ->
                    var showProperties by remember { mutableStateOf(false) }
                    Text(text = beer.name,
                        modifier = Modifier.clickable { showProperties = !showProperties},
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                        )
                    if(showProperties) BeerDetail(beer = beer)

                }
            }


        }

    }
