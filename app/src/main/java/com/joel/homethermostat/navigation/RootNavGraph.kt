package com.joel.homethermostat.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Graph.Auth.route,
        modifier = modifier
    ) {
        authGraph(navController)
        homeGraph(navController)
    }
}