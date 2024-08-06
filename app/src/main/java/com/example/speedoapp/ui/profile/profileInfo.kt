package com.example.speedoapp.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedoapp.R
import com.example.speedoapp.ui.common.ListItemOfInfo
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle
import com.example.speedoapp.utils.readJsonFromAssets
import com.google.gson.Gson
import profileInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfo(navController: NavController, modifier: Modifier = Modifier) {
    val context= LocalContext.current
    val jsonString = readJsonFromAssets(context, "profile.json")

    val jsonData = Gson().fromJson(jsonString, profileInfo::class.java)
    val name=jsonData.name
    val email=jsonData.email
    val date=jsonData.dateOfBirth
    val country= jsonData.country
    val account= jsonData.account
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Profile Information", style = SubTitleTextStyle)
                }, colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = OffYellowColor
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .padding(20.dp)
                    .clickable { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(OffYellowColor)) {
            ListItemOfInfo(title = "Name", description =name)
            ListItemOfInfo(title = "Email", description = email)
            ListItemOfInfo(title = "Date of birth", description = date)
            ListItemOfInfo(title = "Country", description = country)
            ListItemOfInfo(title = "Bank Account", description = account)
            
        }

    }
}
@Preview(showBackground = true)
@Composable
fun ProfileInfoPreview(){
    val nav= rememberNavController()
    ProfileInfo(navController =nav )
}