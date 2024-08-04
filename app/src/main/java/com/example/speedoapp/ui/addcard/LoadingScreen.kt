package com.example.speedoapp.ui.addcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.speedoapp.navigation.AppRoutes
import com.example.speedoapp.ui.theme.CircularIndicatorBig
import com.example.speedoapp.ui.theme.PrimaryColor
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(246.dp)
                .padding(18.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(174.dp),
                color = PrimaryColor,
                trackColor = CircularIndicatorBig,

                )
            Text(
                text = "Speedo \n Transfer", textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = "Connecting to Speedo Transfer \n Credit Card",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
        )
    }
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(AppRoutes.OTP_ROUTE)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoadingScreenPreview(){
//    LoadingScreen()
//}