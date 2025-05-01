package com.joel.flySyApp.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.joel.flySyApp.feature.home.HomeScreen

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        startDestination = Graph.Home.Main,
        route = Graph.Home.route
    ) {
        composable(Graph.Home.Main) {
            HomeScreen(
                onLogOut = {
                    navController.navigate(Graph.Auth.route){
                        popUpTo(Graph.Home.route) {inclusive = true}
                    }
                }
            )
        }
    }
}
