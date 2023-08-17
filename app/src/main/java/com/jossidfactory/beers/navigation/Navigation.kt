package com.jossidfactory.beers.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jossidfactory.beers.screen.detail.DetailScreen
import com.jossidfactory.beers.screen.detail.DetailViewModel
import com.jossidfactory.beers.screen.home.HomeScreen
import com.jossidfactory.beers.screen.home.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(Screen.HomeScreen.route) {
            val homeViewModel = HomeViewModel()
            HomeScreen(navController, homeViewModel)
        }
        composable(Screen.DetailScreen.route) { backStackEntry ->
            val arguments = backStackEntry.arguments
            val beerId = arguments?.getString("beerId")
            beerId?.let { id ->
                val detailViewModel = DetailViewModel(id)
                DetailScreen(navController, detailViewModel)
            }
        }
    }
}