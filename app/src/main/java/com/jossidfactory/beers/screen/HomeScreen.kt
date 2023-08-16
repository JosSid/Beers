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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jossidfactory.beers.R
import com.jossidfactory.beers.component.ButtonBase
import com.jossidfactory.beers.component.LogoApp
import com.jossidfactory.beers.component.TextFieldBase
import com.jossidfactory.beers.model.Beer
import com.jossidfactory.beers.navigation.Screen
import com.jossidfactory.beers.service.RetrofitHelper

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    val retrofit = RetrofitHelper.getInstance()
    var searchValue by rememberSaveable { mutableStateOf("") }
    var beers by rememberSaveable { mutableStateOf(emptyList<Beer>()) }

    var filteredBeers by rememberSaveable { mutableStateOf(beers) }

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
                TextFieldBase(text = stringResource(R.string.search), textValue = searchValue) {
                        newValue ->
                    searchValue = newValue
                }
                Spacer(modifier = Modifier.padding(10.dp))
                ButtonBase(text = stringResource(R.string.clear), onClick =  { searchValue = ""})
                Spacer(modifier = Modifier.padding(10.dp))
                if(filteredBeers.isEmpty()) {
                    Text(text = stringResource(R.string.not_beers),)
                }
            }
            items(filteredBeers) { beer ->
                Text(text = beer.name,
                    modifier = Modifier.clickable { navController.navigate(
                        "detail_screen/${beer.id}") {
                        popUpTo(Screen.HomeScreen.route) {
                            inclusive = true
                        }
                    }},
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

    }
}