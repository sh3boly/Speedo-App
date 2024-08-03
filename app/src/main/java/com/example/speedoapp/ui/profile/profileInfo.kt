package com.example.speedoapp.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedoapp.R
import com.example.speedoapp.ui.addcard.CardInfo
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfo(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Profile Information", style = SubTitleTextStyle)
                }, colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = OffYellowColor
                )
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(OffYellowColor)) {
            ListItemOfInfo(title = "Full Name", description ="Asmaa Dosuky")
            ListItemOfInfo(title = "Email", description = "Asmaa@gmail.com")
            ListItemOfInfo(title = "Date of birth", description = "12/01/2000")
            ListItemOfInfo(title = "Country", description = "Egypt")
            ListItemOfInfo(title = "Bank Account", description = "1234xxxx")
            
        }

    }
}
@Composable
fun ListItemOfInfo(
    title: String,
    description: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 1.dp),
        colors = CardDefaults.cardColors(OffYellowColor),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .size(180.dp, 50.dp)){
                Text(text = title, fontSize = 16.sp, modifier = Modifier.padding(bottom=13.dp),
                    fontWeight = FontWeight.Bold )
                Text(text = description, fontSize = 16.sp, color= Color.Gray)
                BorderStroke(1.dp, color = Color.Gray)
            }

    }
}
@Preview(showBackground = true)
@Composable
fun ProfileInfoPreview(){
    ProfileInfo()
}