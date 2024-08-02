package com.example.speedoapp.ui.MyCards

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speedoapp.R
import com.example.speedoapp.ui.addcard.AddCardViewModel
import com.example.speedoapp.ui.addcard.CardInfo
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.theme.ButtonTextColor
import com.example.speedoapp.ui.theme.P50
import com.example.speedoapp.ui.theme.SubTitleTextStyle


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyCards(modifier: Modifier = Modifier, cardViewModel: AddCardViewModel = viewModel()) {

    val addedCards by rememberUpdatedState(cardViewModel.addedCards)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "My Cards", style = SubTitleTextStyle)
            })
        },
        modifier = Modifier.background(ButtonTextColor)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            addedCards.forEach { cardInfo ->
                Card(
                    colors = CardDefaults.cardColors(P50),
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 245.dp)
                        .height(94.dp)
                        .width(360.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(ButtonTextColor)
                                .align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.bank),
                                contentDescription = "Bank Icon",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = cardInfo.cardHolder,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                            )
                            Spacer(modifier = Modifier.padding(8.dp))
                            Text(
                                text = cardInfo.cardNo,
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
                PrimaryButton(
                    onClick = { /*TODO*/ }, buttonText = "Add new Account",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MyCardPreview() {
    MyCards()
}
