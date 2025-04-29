package com.joel.flySyApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.joel.flySyApp.navigation.RootNavGraph
import com.joel.flySyApp.ui.theme.ThermostatTheme

@Composable
fun App() {
    ThermostatTheme {
       val navController = rememberNavController()
        Scaffold (
            topBar = {},
            bottomBar= {},
            floatingActionButton = {},
        ) { innerPadding ->
            RootNavGraph(
                navController = navController,
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainer)
                    .padding(innerPadding)
            )
        }
    }
}