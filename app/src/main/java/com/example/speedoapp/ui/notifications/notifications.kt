package com.example.speedoapp.ui.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedoapp.R
import com.example.speedoapp.ui.common.MenuAppBar
import com.example.speedoapp.ui.theme.ButtonTextColor
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.P50
import com.example.speedoapp.ui.theme.SubTitleTextStyle
import com.example.speedoapp.utils.readJsonFromAssets
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionListScreen(navController:NavController) {
    var notifications by remember { mutableStateOf<List<Notification>?>(null) }
    val context = LocalContext.current
    val jsonString = readJsonFromAssets(context, "notifications.json")
    if (jsonString.isNotEmpty()) {
        try {
            val gson = Gson()
            val notificationsList = gson.fromJson(jsonString, notificationsList::class.java)
            notifications = notificationsList.notifications
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Notifications", style = SubTitleTextStyle) },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = OffYellowColor)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .padding(20.dp)
                    .clickable { navController.popBackStack() }
            )
        }, bottomBar = { MenuAppBar(modifier = Modifier, currentScreen = "transactions", navController = navController) },
        modifier = Modifier.background(ButtonTextColor)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(OffYellowColor)
                .padding(innerPadding)
        ) {
            if (notifications != null) {
                LazyColumn {
                    items(notifications!!) { notification ->
                        val title = notification.type
                        val subtitle = notification.sender
                        val date = notification.time

                        Card(
                            colors = CardDefaults.cardColors(P50),
                            modifier = Modifier
                                .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                                .height(107.dp)
                                .width(360.dp)
                                .clickable {}
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(RectangleShape)
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.visa_icon),
                                        contentDescription = "transactions",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = title,
                                        fontSize = 14.sp,
                                    )
                                    Text(
                                        text = subtitle,
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = date,
                                        fontSize = 12.sp,
                                        color = Color.LightGray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

