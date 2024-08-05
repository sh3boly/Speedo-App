package com.example.speedoapp.ui.help

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speedoapp.ui.theme.AppTextStyleSelected
import com.example.speedoapp.ui.theme.G900
import com.example.speedoapp.ui.theme.P300
import com.example.speedoapp.ui.theme.P50

@Composable
fun HelpItem(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes image: Int,
    isCall: Boolean = false,
    phoneNumber: String? = null,
    contentDescription: String,
    identifier: Int,
    viewModel: HelpViewModel = viewModel()
) {
    val context = LocalContext.current
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .height(139.19.dp)
            .width(120.dp)
            .clickable {
                viewModel.help(identifier, context)
            }
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = modifier.height(14.6.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color = P50)
            ) {
                Image(
                    painter = painterResource(id = image),
                    colorFilter = ColorFilter.tint(P300),
                    contentDescription = contentDescription,
                    modifier = modifier.size(24.dp)
                )
            }
            Spacer(modifier = modifier.height(14.84.dp))
            Text(text = text, style = AppTextStyleSelected, color = G900)
            if (isCall) Text(text = phoneNumber!!, style = AppTextStyleSelected, color = P300)

        }
    }
}
