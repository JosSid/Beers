package com.jossidfactory.beers.navigation

enum class Screen(val route: String) {
    HomeScreen("home_screen"),
    DetailScreen("detail_screen/{beerId}")
}