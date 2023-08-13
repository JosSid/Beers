package com.jossidfactory.beers.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jossidfactory.beers.BeerDetail
import com.jossidfactory.beers.component.ButtonBase
import com.jossidfactory.beers.component.LogoApp
import com.jossidfactory.beers.component.TextFieldBase
import com.jossidfactory.beers.model.Beer
import com.jossidfactory.beers.service.RetrofitHelper

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){
    val retrofit = RetrofitHelper.getInstance()
    var searchValue by remember { mutableStateOf("") }
    var beers by remember { mutableStateOf(emptyList<Beer>()) }

    var filteredBeers by remember { mutableStateOf(beers) }

    LaunchedEffect(key1 = searchValue) {
        try {
            beers = retrofit.getBeers()
        } catch (e: Exception) {
            // Manejar errores de red aquí
        }
        if (searchValue.isNotEmpty()) {
            filteredBeers = beers.filter { beer -> beer.name.lowercase().contains(searchValue.lowercase())}
        } else {
            filteredBeers = beers
        }
    }
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                LogoApp()
                Spacer(modifier = Modifier.padding(10.dp))
                TextFieldBase(text = "Search", textValue = searchValue) { newValue ->
                    searchValue = newValue
                }
                Spacer(modifier = Modifier.padding(10.dp))
                ButtonBase(text = "Clear", onClick =  { searchValue = ""})
                Spacer(modifier = Modifier.padding(10.dp))
            }
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