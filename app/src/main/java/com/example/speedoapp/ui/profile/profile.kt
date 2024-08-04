package com.example.speedoapp.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedoapp.R
import com.example.speedoapp.ui.addcard.AddCardViewModel
import com.example.speedoapp.ui.addcard.CardInfo
import com.example.speedoapp.ui.theme.G100
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.PrimaryColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController, modifier: Modifier = Modifier, cardViewModel: AddCardViewModel = viewModel()) {
    val cardInfo by cardViewModel.cardInfo.collectAsState()
    val demoCardInfo = CardInfo(
        userID = "user123",
        cardHolder = "John Doe",
        cardNo = "1234567890123456",
        expiryDate = "11\\25",
        CVV = "123",
    )
    val democardInfo by remember { mutableStateOf(demoCardInfo) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Profile", style = SubTitleTextStyle)
            },colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = OffYellowColor))
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(OffYellowColor)) {
            Row(modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .align(Alignment.CenterVertically)
                        .alpha(0.3f),
                ) {
                    Text(
                        text = "JD",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 12.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = democardInfo.cardHolder,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
            ListItem(iconRes = R.drawable.group_18325, title = "Personal information", description ="Your information", onClick = {})
            ListItem(iconRes = R.drawable.group_183252, title = "Settings", description ="Change your settings", onClick = {} )
            ListItem(iconRes = R.drawable.group_183253, title = "Payment history", description = "view your transactions", onClick = {})
            ListItem(iconRes = R.drawable.group_183254, title = "My Favourite list", description ="view your favourite", onClick = {} )

        }
    }
}

@Composable
fun ListItem(
    iconRes: Int,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth().padding(bottom = 1.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(OffYellowColor),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(painter = painterResource(id = iconRes), contentDescription = title)
            Column(modifier = Modifier.padding(start = 24.dp, end=90.dp).size(180.dp,50.dp)){
                Text(text = title, fontSize = 16.sp, modifier = Modifier.padding(bottom=4.dp),
                    fontWeight = FontWeight.Bold )
                Text(text = description, fontSize = 16.sp, color= Color.Gray)
            }
            Image(painter = painterResource(id = R.drawable.path),
                contentDescription ="to",
                modifier = Modifier.size(13.dp))
        }

    }
}