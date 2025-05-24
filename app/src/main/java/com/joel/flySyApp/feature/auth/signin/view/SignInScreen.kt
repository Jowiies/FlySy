package com.joel.flySyApp.feature.auth.signin.view

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joel.flySyApp.R
import com.joel.flySyApp.feature.auth.signin.viewmodel.SignInViewModel
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    onSignUpClick: () -> Unit,
    onSignUpWithGoogle: () -> Unit,
    onForgotPassword: () -> Unit,
    onSignInSuccess: (String) -> Unit,
    modifier: Modifier,
) {
    val viewModel: SignInViewModel = viewModel()
    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val signInEnabled: Boolean by viewModel.signInEnabled.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current


    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        if (isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
        else {
            Column (
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(20.dp),
            ){
                TitleIcon()

                SignInText()

                Spacer(Modifier.height(10.dp))

                StyledTextField(
                    value = email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = "Email",
                    placeholder = "example@domain.com",
                    keyboardType = KeyboardType.Email,
                    isError = email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches(),
                )

                Spacer(modifier = Modifier.height(5.dp))

                StyledTextField(
                    value = password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = "Password",
                    placeholder = "Enter your password",
                    keyboardType = KeyboardType.Password,
                    isPassword = true
                )

                Spacer(Modifier.height(8.dp))

                ForgotPasswordText(onForgotPassword)

                Spacer(Modifier.height(16.dp))

                SignInButton(signInEnabled){
                    coroutineScope.launch {
                        viewModel.onSignInSelected(
                            onSuccess = { token ->
                                onSignInSuccess(token.access_token)
                            },
                            onError = { error ->
                                Toast.makeText(context, "Error logging in", Toast.LENGTH_SHORT).show()
                                Log.e("SignInError", "Error during sign in", error)

                            }
                        )
                    }
                }

                Spacer(Modifier.height(32.dp))

                HorizontalLoginDivider(Modifier.align(Alignment.CenterHorizontally))

                Spacer(Modifier.height(32.dp))

                SignGoogleButton(onSignUpWithGoogle)

                CreateAccountText(onSignUpClick)

                Spacer(Modifier.height(10.dp))
            }

        }
    }
}

@Composable
fun CreateAccountText(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        TextButton(
            onClick = onClick
        ) {
            Text("Create account")
        }
    }
}

@Composable
fun HorizontalLoginDivider(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.outline
        )

        Text(
            text = "OR",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.outline
        )
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
            .clip(shape = RoundedCornerShape(30.dp))
            .border(
                shape = RoundedCornerShape(30.dp),
                width = 2.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
            .background(color = Color.White),
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
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        shape = MaterialTheme.shapes.medium,
        textStyle = MaterialTheme.typography.bodyLarge,
        singleLine = true,
        isError = isError,
        visualTransformation =
            if (isPassword && !passwordVisibility)
                PasswordVisualTransformation()
            else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                val image = if (passwordVisibility)
                    Icons.Default.Visibility
                else Icons.Default.VisibilityOff

                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility }
                ) {
                    Icon(
                        imageVector = image,
                        contentDescription =
                            if (passwordVisibility)
                                "Hide password"
                            else "Show password")
                }
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ForgotPasswordText(onClick: () -> Unit ){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top

    ){
        TextButton(
            onClick = onClick
        ) {
            Text("Forgot password")
        }
    }
}

@Composable
fun SignInButton(
    signInEnabled: Boolean,
    onSignInSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Top
    ){

        Button(
            onClick = onSignInSelected,
            enabled = signInEnabled
        ) {Text("Sign in") }
    }
}

@Composable
fun SignGoogleButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    )
    {
        Image(
            painter = painterResource(
                if (isSystemInDarkTheme()) R.drawable.ic_google_si_btn_dark
                else R.drawable.ic_google_si_btn_light
            ),
            contentDescription = "Sign in with Google",
            modifier = Modifier
                .height(56.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .clickable { onClick() },
            contentScale = ContentScale.FillHeight
        )

    }
}

