package com.jossidfactory.beers.screen.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jossidfactory.beers.R
import com.jossidfactory.beers.component.ButtonBase
import com.jossidfactory.beers.component.LogoApp
import com.jossidfactory.beers.model.Beer
import com.jossidfactory.beers.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, detailViewModel: DetailViewModel) {

    val beers: List<Beer> by detailViewModel.beers.observeAsState(initial = emptyList())

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
                beers.forEach { beer ->
                    Text(
                        text = "${beer.name}",
                        fontSize = 20.sp,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 5.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                    AsyncImage(model = beer.image_url, contentDescription = "image", modifier =
                    Modifier.height(200.dp))
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "${beer.description}",
                        modifier = Modifier.padding(10.dp))
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(text = "${stringResource(R.string.alcohol_content)} ${beer.abv}")
                    Spacer(modifier = Modifier.padding(10.dp))
                    ButtonBase(text = stringResource(R.string.back_to_list), onClick =  { navController
                        .navigate(Screen
                        .HomeScreen.route) {
                        popUpTo(Screen.DetailScreen.route) {
                            inclusive = true
                        }
                    }})
                }
            }
        }
    }
}