package com.joel.flySyApp

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.joel.flySyApp.navigation.RootNavGraph
import com.joel.flySyApp.ui.theme.FlySyAppTheme

@Composable
fun App() {
    FlySyAppTheme {
       val navController = rememberNavController()
        Scaffold (
            topBar = {},
            bottomBar= {},
            floatingActionButton = {},
        ) { innerPadding ->
            RootNavGraph(
                navController = navController,
                paddingValues = innerPadding
            )
        }
    }
}