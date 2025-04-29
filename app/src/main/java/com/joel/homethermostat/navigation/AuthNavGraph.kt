package com.joel.homethermostat.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.navigation.navigation
import com.joel.homethermostat.feature.auth.LoginScreen

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(
        startDestination = "login_screen",
        route = Graph.Auth.route,
    ) {
        composable("login_screen") { LoginScreen(navController) }
    }
}