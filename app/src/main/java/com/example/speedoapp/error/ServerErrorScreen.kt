package com.example.speedoapp.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.speedoapp.R
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.common.SecondaryButton
import com.example.speedoapp.ui.theme.BodyMedium
import com.example.speedoapp.ui.theme.G700
import com.example.speedoapp.ui.theme.G900
import com.example.speedoapp.ui.theme.HeadingTextStyle
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.RedYellowColor

@Composable
fun ServerErrorScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(OffYellowColor, RedYellowColor)
                )
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_errorr),
                contentDescription = stringResource(R.string.server_error)
            )
            Spacer(modifier = modifier.height(53.87.dp))
            Text(
                text = stringResource(R.string.server_error),
                style = HeadingTextStyle,
                color = G900,
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = stringResource(R.string.server_error_message),
                style = BodyMedium,
                color = G700,
            )
            Spacer(modifier = modifier.height(32.dp))
            PrimaryButton(
                onClick = { /*TODO*/ },
                buttonText = stringResource(id = R.string.call_us)
            )
            Spacer(modifier = modifier.height(16.dp))
            SecondaryButton(
                onClick = { /*TODO*/ },
                buttonText = stringResource(R.string.message_us)
            )
        }
    }
}