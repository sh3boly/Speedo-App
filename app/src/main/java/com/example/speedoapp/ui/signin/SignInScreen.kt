package com.example.speedoapp.ui.signin


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.speedoapp.R
import com.example.speedoapp.ui.common.DataField
import com.example.speedoapp.ui.common.PasswordField
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.theme.AppTextStyle
import com.example.speedoapp.ui.theme.ClickAbleTextStyle
import com.example.speedoapp.ui.theme.DisabledColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle
import com.example.speedoapp.ui.theme.TitleTextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedoapp.api.RetrofitFactory
import com.example.speedoapp.model.LoginStatus
import com.example.speedoapp.navigation.AppRoutes
import com.example.speedoapp.navigation.AppRoutes.HOME_ROUTE
import com.example.speedoapp.ui.signup.AuthViewModel
import com.example.speedoapp.ui.theme.GradientEnd
import com.example.speedoapp.ui.theme.GradientStart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Sign In", style = SubTitleTextStyle)
            })
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            GradientStart,
                            GradientEnd
                        )
                    )
                )
                .fillMaxSize()
                .background(brush = Brush.linearGradient(listOf(GradientStart, GradientEnd)))
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            //val passwordError by viewModel.passwordError.collectAsState()
            //val nameError by viewModel.nameError.collectAsState()
            val loginStatus by viewModel.loginStatus.collectAsState()
            val context = LocalContext.current

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Speedo Transfer", style = TitleTextStyle)

            Spacer(modifier = Modifier.height(55.86.dp))

            DataField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                image = R.drawable.ic_email,
                typingImage = R.drawable.ic_email_typing,
                type = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = "Enter your password"
            )
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(onClick = {
                viewModel.login(email, password, context)

            }, buttonText = "Sign In")
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text = "Don't have an account? ",
                    style = AppTextStyle,
                    color = DisabledColor
                )
                Text(
                    text = "Sign Up",
                    style = ClickAbleTextStyle,
                    modifier = Modifier.clickable { navController.navigate(AppRoutes.SIGNUP_ROUTE) })
            }

            LaunchedEffect(loginStatus) {
                loginStatus?.let { status ->
                    when (status) {
                        is LoginStatus.Success -> {
                            Toast.makeText(
                                context,
                                "login Successful!",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(HOME_ROUTE)
                        }
                        is LoginStatus.Error -> {
                            Toast.makeText(
                                context,
                                "Sign Up failed! Error: ${status.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                            viewModel.resetLoginStatus()

                        }
                    }
                }
            }

        }
    }
}
