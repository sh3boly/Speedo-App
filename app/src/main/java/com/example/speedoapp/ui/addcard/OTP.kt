package com.example.speedoapp.ui.addcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedoapp.navigation.AppRoutes
import com.example.speedoapp.ui.OTPfiles.OTPTextField
import com.example.speedoapp.ui.OTPfiles.OtpTextFieldDefaults
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.signup.AuthViewModel
import com.example.speedoapp.ui.theme.AppTextStyle
import com.example.speedoapp.ui.theme.ClickAbleTextStyle
import com.example.speedoapp.ui.theme.DisabledColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTP(navController: NavController, modifier: Modifier = Modifier, viewModel: AuthViewModel = viewModel()) {
    var otp by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Bank Card OTP", style = SubTitleTextStyle)
            })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Enter the digits verification code \n sent to ${viewModel.emailError.toString()}",
                textAlign = TextAlign.Center, modifier = Modifier.padding(top=45.dp, bottom = 32.dp))

            OTPTextField(
                modifier = Modifier.padding(start=48.dp,end=48.dp, bottom = 48.dp),
                value = otp,
                onTextChanged = { otp = it },
                numDigits = 6,
                isMasked = false,
                digitContainerStyle = OtpTextFieldDefaults.outlinedContainer(),
                textStyle = MaterialTheme.typography.titleLarge,
                isError = false
            )
            Row(modifier = Modifier.padding(bottom = 156.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Don't receive OTP? ",
                    style = AppTextStyle,
                    color = DisabledColor
                )
                Text(text = "Resend OTP", style = ClickAbleTextStyle)
            }
            PrimaryButton(onClick = {navController.navigate(AppRoutes.OTP_CONNECT_ROUTE) }, buttonText ="Sign on", modifier = Modifier.padding(15.dp))

        }
        }
}