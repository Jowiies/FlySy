package com.joel.homethermostat.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.navigation.navigation

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        startDestination = "home_screen",
        route = Graph.Home.route
    ) {

    }
}
