package com.joel.flySyApp.feature.auth

import android.widget.GridLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joel.flySyApp.R

@Composable
fun LoginScreen(navController: NavController) {
    PreviewContent()
}

@Preview(
    showSystemUi = true,
    device = "id:pixel_7a",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES or android.content.res.Configuration.UI_MODE_TYPE_UNDEFINED,
    wallpaper = androidx.compose.ui.tooling.preview.Wallpapers.BLUE_DOMINATED_EXAMPLE
)
@Composable
fun PreviewContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column (
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(20.dp),
        ){
            TitleIcon()

            SignInText()

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            StyledTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                placeholder = "example@domain.com",
                keyboardType = KeyboardType.Email,
                isError = email.isNotEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(),
                modifier = Modifier.padding(top = 10.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            StyledTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                placeholder = "Enter your password",
                keyboardType = KeyboardType.Password,
                isPassword = true
            )


            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp),
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
fun SignInText() {
    Text(
        text = "Sign in",
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(top = 10.dp)
    )
    Text(
        text = "with your Flysy Account",
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun TitleIcon() {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.surfaceTint),
        contentAlignment = Alignment.Center,
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "The Flysy logo",
            modifier = Modifier
                .padding(10.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun StyledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme.typography.bodyLarge,
        singleLine = true,
        isError = isError,
        visualTransformation = if (isPassword && !passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisibility)
                    Icons.Default.Visibility
                else Icons.Default.VisibilityOff

                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(imageVector = image, contentDescription = if (passwordVisibility) "Hide password" else "Show password")
                }
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun LoginButtonRow() {
    Row(
        
    ){
        Button(
            onClick = {},
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
    }
}