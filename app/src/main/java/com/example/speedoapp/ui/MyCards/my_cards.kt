package com.example.speedoapp.ui.MyCards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cardList
import com.example.speedoapp.R
import com.example.speedoapp.model.CardInfo
import com.example.speedoapp.navigation.AppRoutes
import com.example.speedoapp.ui.common.MenuAppBar
import com.example.speedoapp.ui.common.MycardsItem
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.PrimaryColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle
import com.example.speedoapp.utils.readJsonFromAssets
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCards(
    navController: NavController,
) {
    val context = LocalContext.current
    var card by remember { mutableStateOf<List<CardInfo>?>(null) }
    val jsonString = readJsonFromAssets(context, "cardList.json")

    if (jsonString.isNotEmpty()) {
        try {
            val gson = Gson()
            val x= cardList::class.java
            val cardListObject = gson.fromJson(jsonString,x)
            card = cardListObject?.Cards
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "My cards", style = SubTitleTextStyle)
            })
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .padding(20.dp)
                    .clickable { navController.popBackStack() }
            )
        }, bottomBar = { MenuAppBar(modifier = Modifier, currentScreen = "mycards", navController = navController) },
        modifier = Modifier.background(OffYellowColor)

    ) { innerPadding ->
        if(card!=null) {
            Button(onClick = { navController.navigate(AppRoutes.ADDCARD_ROUTE) }) {
                Text(text = "add Account", textAlign = TextAlign.Center)
            }
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding).fillMaxSize(),
            ) {
                items(card!!) { cardInfo ->
                    MycardsItem(text1 = cardInfo.cardHolder, text2 = cardInfo.cardNo)
                }
                item {
                    Button(
                        onClick = { navController.navigate(AppRoutes.ADDCARD_ROUTE) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor
                            = PrimaryColor,
                            contentColor = Color.White
                        )) {
                        Text(text = "Add Account", textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MyCardsPreview(){
    val nav = rememberNavController()
    MyCards(navController =nav )
}
