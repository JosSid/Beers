package com.jossidfactory.beers.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jossidfactory.beers.screen.DetailScreen
import com.jossidfactory.beers.screen.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(Screen.DetailScreen.route) { backStackEntry ->
            val arguments = backStackEntry.arguments
            val beerId = arguments?.getString("beerId")
            beerId?.let { id ->
                DetailScreen(navController, id)
            }
        }
    }
}