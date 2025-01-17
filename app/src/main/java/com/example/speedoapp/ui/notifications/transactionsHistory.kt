package com.example.speedoapp.ui.notifications

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.speedoapp.model.CardInfo
import com.example.speedoapp.model.Transaction
import com.example.speedoapp.model.TransactionHistory
import com.example.speedoapp.model.TransactionHistoryRoot
import com.example.speedoapp.model.TransactionRoot
import com.example.speedoapp.navigation.AppRoutes
import com.example.speedoapp.ui.common.BoxList
import com.example.speedoapp.ui.common.ListItem
import com.example.speedoapp.ui.common.MenuAppBar
import com.example.speedoapp.ui.homepage.HomeViewModel
import com.example.speedoapp.ui.theme.ButtonTextColor
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle
import com.example.speedoapp.utils.readJsonFromAssets
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionHistroy(modifier: Modifier = Modifier, navController: NavController, viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Transactions", style = SubTitleTextStyle)
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
        },
        bottomBar = {
            MenuAppBar(
                modifier = Modifier,
                currentScreen = "transactions",
                navController = navController
            )
        },
        modifier = Modifier.background(OffYellowColor)
    )
    //for each
    { innerPadding ->
        val context = LocalContext.current
        var transactions by remember { mutableStateOf<List<TransactionHistory>?>(null) }
        val jsonString = readJsonFromAssets(context, "transactionsHistory.json")
        Log.d("Transaction", "JSON String: $jsonString")
        if (jsonString.isNotEmpty()) {
            try {
                val gson = Gson()
                val x = TransactionHistoryRoot::class.java
                val ListObject = gson.fromJson(jsonString, x)
                transactions = ListObject?.transactions
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        Log.d("Transaction", "JSON String: $transactions")

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(OffYellowColor)
        ) {
            Text(
                text = "Your Last Transactions",
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(18.dp),
                fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
            if (transactions != null) {
                LazyColumn {
                    items(transactions!!) { transaction ->
                        BoxList(
                            icon = if (transaction.cardType == "Credit Card") R.drawable.group_18312 else R.drawable.ic_bank,
                            title = transaction.name,
                            status = transaction.status,
                            description = "${transaction.cardType} - ${transaction.cardNumber}\n" +
                                    "${transaction.amount}",
                            date = viewModel.formatDateTime(transaction.transactionDate),
                            OnClick = { navController.navigate(AppRoutes.TRANSACTIONS_DETAILS) }
                        )
                    }
                }
            }
        }
    }
}
