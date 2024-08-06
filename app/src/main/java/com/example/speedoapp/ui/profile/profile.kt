package com.example.speedoapp.ui.profile

import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedoapp.R
import com.example.speedoapp.navigation.AppRoutes
import com.example.speedoapp.ui.addcard.AddCardViewModel
import com.example.speedoapp.model.CardInfo
import com.example.speedoapp.ui.common.ListItem
import com.example.speedoapp.ui.common.MenuAppBar
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle
import com.example.speedoapp.utils.readJsonFromAssets
import com.google.gson.Gson
import profileInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController, modifier: Modifier = Modifier) {
    val context= LocalContext.current
    val jsonString = readJsonFromAssets(context, "profile.json")

    val jsonData = Gson().fromJson(jsonString, profileInfo::class.java)
    val name=jsonData.name
    val initial=getInitials(name)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Profile", style = SubTitleTextStyle)
            },colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = OffYellowColor))
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .padding(20.dp)
                    .clickable { navController.popBackStack() }
            )
        }, bottomBar = { MenuAppBar(modifier = Modifier, currentScreen = "transactions", navController = navController) },
        modifier = Modifier.background(OffYellowColor)
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
                        text = initial,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 12.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
            ListItem(iconRes = R.drawable.group_18325, title = "Personal information", description ="Your information", onClick = {navController.navigate(AppRoutes.PROFILE_INFO)},
                OffYellowColor)
            ListItem(iconRes = R.drawable.group_183252, title = "Settings", description ="Change your settings",
                onClick = {navController.navigate(AppRoutes.SETTINGS)}, OffYellowColor )
            ListItem(iconRes = R.drawable.group_183253, title = "Payment history", description = "view your transactions", onClick = {},
                OffYellowColor)
            ListItem(iconRes = R.drawable.group_183254, title = "My Favourite list", description ="view your favourite",
                onClick = {}, OffYellowColor)

        }
    }
}

fun getInitials(fullName: String): String {
    val names = fullName.split(" ")
    return names.take(2)
        .map { it.first().uppercaseChar() }
        .joinToString("")
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    val nav= rememberNavController()
    Profile(navController =nav )
}