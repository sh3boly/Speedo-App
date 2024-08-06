package com.example.speedoapp.ui.notifications

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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedoapp.R
import com.example.speedoapp.ui.common.AccountCard
import com.example.speedoapp.ui.common.MenuAppBar
import com.example.speedoapp.ui.common.MycardsItem
import com.example.speedoapp.ui.common.TransactionList
import com.example.speedoapp.ui.common.cardimp
import com.example.speedoapp.ui.theme.BodyMedium
import com.example.speedoapp.ui.theme.BodyMediumBold
import com.example.speedoapp.ui.theme.ButtonTextColor
import com.example.speedoapp.ui.theme.DisabledColor
import com.example.speedoapp.ui.theme.G100
import com.example.speedoapp.ui.theme.G500
import com.example.speedoapp.ui.theme.G900
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.P50
import com.example.speedoapp.ui.theme.PrimaryColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle
import com.example.speedoapp.ui.theme.SubTitleTextStyleBold
import com.example.speedoapp.ui.tranfer.AmountScreenViewModel
import com.example.speedoapp.utils.readJsonFromAssets
import com.google.gson.Gson
import transData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetails(navController:NavController) {
    val context= LocalContext.current
    val jsonString = readJsonFromAssets(context, "transdata.json")

    val jsonData = Gson().fromJson(jsonString, transData::class.java)
    val Rname=jsonData.Rname
    val Sname=jsonData.Sname
    val Raccount=jsonData.RaccountNumber
    val Saccount=jsonData.SaccountNumber
    val date= jsonData.date
    val reference=jsonData.reference
    val amount=jsonData.transferAmount
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Successful Transactions", style = SubTitleTextStyle)
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
        }, bottomBar = { MenuAppBar(modifier = Modifier, currentScreen = "transactions", navController = navController) },
        modifier = Modifier.background(ButtonTextColor)
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(OffYellowColor)
        ) {
            TransactionList(amount)
            Spacer(modifier = Modifier.padding(24.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Column {
                    cardimp(Sname,Saccount)
                    cardimp(Rname,Raccount)
                }
                Image(
                    painter = painterResource(R.drawable.ic_success_transaction),
                    contentDescription = null,
                    modifier = Modifier
                        .size(44.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier
                        .background(ButtonTextColor)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    Text(text = "Reference")
                    Text(text = reference)
                }
                Row(
                    modifier = Modifier
                        .background(ButtonTextColor)
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Date")
                    Text(text = date)
                }
            }
        }
    }
}
