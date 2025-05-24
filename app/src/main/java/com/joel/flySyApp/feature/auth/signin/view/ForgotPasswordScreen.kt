package com.joel.flySyApp.feature.auth.signin.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ForgotPasswordScreen(onBack: () -> Unit, onSubmit: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onBack
        ) {
            Text(
                text = "LogOut"
            )
        }
    }
}
