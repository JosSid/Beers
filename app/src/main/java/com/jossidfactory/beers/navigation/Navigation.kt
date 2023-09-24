package com.jossidfactory.beers.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jossidfactory.beers.screen.detail.DetailScreen
import com.jossidfactory.beers.screen.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen {
                navController.navigate(
                    "detail_screen/$it"
                ) {
                    popUpTo(Screen.HomeScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.DetailScreen.route) { backStackEntry ->
            val arguments = backStackEntry.arguments
            val beerId = arguments?.getString("beerId")
            beerId?.let { id ->
                DetailScreen(id) {
                    navController
                        .navigate(
                            Screen
                                .HomeScreen.route
                        ) {
                            popUpTo(Screen.DetailScreen.route) {
                                inclusive = true
                            }
                        }
                }
            }
        }
    }
}