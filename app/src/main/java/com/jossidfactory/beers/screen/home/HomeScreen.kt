package com.jossidfactory.beers.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jossidfactory.beers.R
import com.jossidfactory.beers.component.ButtonBase
import com.jossidfactory.beers.component.LogoApp
import com.jossidfactory.beers.component.TextFieldBase
import com.jossidfactory.beers.model.Beer
import com.jossidfactory.beers.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory()
    )

    val searchValue: String by homeViewModel.searchValue.observeAsState(initial = "")

    val isLoading: Boolean by homeViewModel.isLoadig.observeAsState(initial = false)
    
    val filteredBeers: List<Beer> by homeViewModel.filteredBeers.observeAsState(initial =
    emptyList())

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
                TextFieldBase(text = stringResource(R.string.search), textValue = searchValue,
                    onTextValueChange = { homeViewModel.onSearchChange(it)})
                Spacer(modifier = Modifier.padding(10.dp))
                ButtonBase(text = stringResource(R.string.clear), onClick =  { homeViewModel.onSearchChange("")})
                Spacer(modifier = Modifier.padding(10.dp))
                if(filteredBeers.isEmpty()) {
                    Text(text = stringResource(R.string.not_beers))
                }
                if (isLoading){
                    Spacer(modifier = Modifier.padding(30.dp))
                    CircularProgressIndicator()
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