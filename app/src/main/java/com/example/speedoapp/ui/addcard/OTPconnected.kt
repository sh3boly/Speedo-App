package com.example.speedoapp.ui.addcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedoapp.R
import com.example.speedoapp.navigation.AppRoutes
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.common.SecondaryButton
import com.example.speedoapp.ui.theme.DisabledColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPconnected(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Bank Card OTP", style = SubTitleTextStyle)
            })
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.padding(77.dp))
            Image(painter = painterResource(id = R.drawable.group_18305),
                contentDescription = "Account connected successfully",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .width(124.dp))
            Text(
                text = "Account connected \n successfully!",
                textAlign = TextAlign.Center,
                fontSize =24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top=50.dp, bottom = 16.dp)
            )
            Text(text = "Feel free to connect another account \n at the same time.",
                textAlign = TextAlign.Center,
                fontSize =16.sp,
                color = DisabledColor
                )
            Spacer(modifier = modifier.padding(100.dp))
            PrimaryButton(onClick = {navController.navigate(AppRoutes.ADDCARD_ROUTE) }, buttonText = "connect another account",
                modifier = Modifier.height(51.dp).width(344.dp))
            Spacer(modifier = Modifier.padding(8.dp))
            SecondaryButton(onClick = {navController.navigate(AppRoutes.HOME_ROUTE)}, buttonText ="Back to Home")
        }

        }
}