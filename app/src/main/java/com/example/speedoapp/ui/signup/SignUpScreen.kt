package com.example.speedoapp.ui.signup


import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
import com.example.speedoapp.navigation.AppRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel()
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Sign Up", style = SubTitleTextStyle)
            })
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            var name by rememberSaveable { mutableStateOf("") }
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            val passwordError by viewModel.passwordError.collectAsState()
            val emailError by viewModel.emailError.collectAsState()
            val nameError by viewModel.nameError.collectAsState()

            Spacer(modifier = Modifier.height(55.14.dp))

            Text(text = "Speedo Transfer", style = TitleTextStyle)

            Spacer(modifier = Modifier.height(65.86.dp))

            DataField(
                isError = nameError,
                value = name,
                onValueChange = { name = it },
                label = "Full Name",
                image = R.drawable.ic_person,
                typingImage = R.drawable.ic_person_typing,
                type = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(8.dp))
            DataField(
                isError = emailError,
                value = email,
                onValueChange = { email = it },
                label = "Email",
                image = R.drawable.ic_email,
                typingImage = R.drawable.ic_email_typing,
                type = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(8.dp))
            PasswordField(
                isError = passwordError,
                value = password,
                onValueChange = {
                    password = it
                },
                label = "Enter your password"
            )
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(onClick = {
                viewModel.validatePassword(password)
                viewModel.validateEmail(email)
                viewModel.validatePassword(password)
                if (viewModel.validatePassword(password)
                    && viewModel.validateEmail(email)
                    && viewModel.validatePassword(password)
                ) {
                    try {
                        navController.navigate("${AppRoutes.COUNTRYDATE_ROUTE}/$name/$email/$password")
                    } catch (e: Exception) {
                        Log.d("Exception", "Error during navigation: ${e.message} ")
                    }
                }
            }, buttonText = "Sign up")
            Spacer(modifier = Modifier.height(16.dp))
            Row() {
                Text(
                    text = "Already have an account? ",
                    style = AppTextStyle,
                    color = DisabledColor
                )
                Text(text = "Sign In", style = ClickAbleTextStyle, modifier = Modifier.clickable {
                    navController.navigate(AppRoutes.SIGNIN_ROUTE)
                })
            }

        }
    }
}
