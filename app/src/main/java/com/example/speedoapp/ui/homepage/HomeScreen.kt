package com.example.speedoapp.ui.homepage


import android.os.Build
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.speedoapp.R
import com.example.speedoapp.model.Transaction
import com.example.speedoapp.ui.common.IconWithText
import com.example.speedoapp.ui.common.MenuAppBar
import com.example.speedoapp.ui.common.UserIcon
import com.example.speedoapp.ui.theme.AppTextStyle
import com.example.speedoapp.ui.theme.AppTextStyleSelected
import com.example.speedoapp.ui.theme.BodyMedium
import com.example.speedoapp.ui.theme.BodyMediumBold
import com.example.speedoapp.ui.theme.CardDetailsTextStyle
import com.example.speedoapp.ui.theme.G0
import com.example.speedoapp.ui.theme.G200
import com.example.speedoapp.ui.theme.G700
import com.example.speedoapp.ui.theme.G900
import com.example.speedoapp.ui.theme.PrimaryColor
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speedoapp.api.InactivityManager
import com.example.speedoapp.model.BalanceStatus
import com.example.speedoapp.model.LoginStatus
import com.example.speedoapp.model.TransactionHistory
import com.example.speedoapp.model.TransactionHistoryRoot
import com.example.speedoapp.navigation.AppRoutes.AMOUNT_TRANSFER
import com.example.speedoapp.navigation.AppRoutes.HOME_ROUTE
import com.example.speedoapp.navigation.AppRoutes.MY_CARDS
import com.example.speedoapp.navigation.AppRoutes.PROFILE
import com.example.speedoapp.navigation.AppRoutes.SIGNIN_ROUTE
import com.example.speedoapp.navigation.AppRoutes.TRANSACTIONS_HISTORY
import com.example.speedoapp.ui.theme.G40
import com.example.speedoapp.ui.theme.HeadingSemiBold
import com.example.speedoapp.ui.theme.HomeGradientEnd
import com.example.speedoapp.ui.theme.HomeGradientStart
import com.example.speedoapp.ui.theme.P50
import com.example.speedoapp.utils.readJsonFromAssets
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {


    val balance by viewModel.balance.collectAsState()
    InactivityManager.initViewModel(viewModel)
    //val transactions by viewModel.transactions.collectAsState()
    //val name by viewModel.name.collectAsState()
    //Log.d("trace", "The name is : $name")
//
//    val hasError by viewModel.hasError.collectAsState()
//    if (hasError > 0)
//        Toast.makeText(LocalContext.current, "Check your connection", Toast.LENGTH_SHORT).show()
    //val name = "Asmaa Desouky"
    val context = LocalContext.current
    val balanceStatus by viewModel.balanceStatus.collectAsState()

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
    Scaffold(
        bottomBar = { MenuAppBar(currentScreen = "home", navController = navController) }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .background(Brush.linearGradient(listOf(HomeGradientStart, HomeGradientEnd)))
                .padding(start = 16.dp, end = 16.dp, bottom = 15.dp, top = 30.dp)
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                UserIcon(initials = viewModel.getInitials(balance!!.name))

                Spacer(modifier = Modifier.padding(8.dp))

                Column() {
                    Text(text = "Welcome back,", style = AppTextStyle, color = PrimaryColor)
                    Text(text = balance!!.name, style = BodyMediumBold, color = G900)
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "com.example.speedoapp.ui.notifications.Notification bell Icon"
                    )
                }

            }
            Spacer(modifier = modifier.padding(15.dp))
            BalanceCard(balance = viewModel.balanceStringify(balance!!.balance))
            Spacer(modifier = modifier.padding(16.dp))
            Services(navController = navController)
            Spacer(modifier = modifier.padding(16.dp))
            if (transactions != null) {
                RecentTransaction(
                    transactions = transactions!!,
                    viewModel = viewModel,
                    navController = navController
                )
            }
            LaunchedEffect(balanceStatus) {
                balanceStatus?.let { status ->
                    when (status) {
                        is BalanceStatus.Success -> {
                        }

                        is BalanceStatus.Error -> {
                            Toast.makeText(
                                context,
                                "Your session has expired",
                                Toast.LENGTH_SHORT
                            ).show()
                            viewModel.logout()
                            navController.navigate(SIGNIN_ROUTE)
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun BalanceCard(modifier: Modifier = Modifier, balance: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = PrimaryColor),
        modifier = modifier
            .fillMaxWidth()
            .height(123.dp),

        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 13.dp, end = 13.dp, top = 25.dp, bottom = 24.dp)

        ) {
            Text(text = "Current Balance", style = BodyMedium, color = G0)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$$balance", style = HeadingSemiBold, color = G0)
        }

    }
}

@Composable
fun Services(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .width(343.dp)
            .height(141.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = G0),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 12.dp)

        ) {
            Text(text = "Services", style = BodyMedium, color = G700)
        }
        Spacer(modifier = modifier.padding(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    start = 12.dp, end = 15.dp, top = 0.dp, bottom = 8.dp
                )

        ) {
            Spacer(modifier = modifier.padding(horizontal = 10.dp))
            IconWithText(
                icon = R.drawable.ic_transfer,
                text = "Transfer",
                onClick = { navController.navigate(AMOUNT_TRANSFER) })
            Spacer(modifier = Modifier.padding(horizontal = 15.dp))

            IconWithText(icon = R.drawable.ic_history, text = "Transactions", onClick = {
                navController.navigate(TRANSACTIONS_HISTORY)
            })
            Spacer(modifier = Modifier.padding(horizontal = 15.dp))

            IconWithText(icon = R.drawable.ic_cards, text = "Cards", onClick = {
                navController.navigate(MY_CARDS)
            })
            Spacer(modifier = Modifier.padding(horizontal = 15.dp))

            IconWithText(icon = R.drawable.ic_account, text = "Account", onClick = {
                navController.navigate(PROFILE)
            })
            Spacer(modifier = Modifier.padding(horizontal = 15.dp))
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecentTransaction(
    modifier: Modifier = Modifier,
    transactions: List<TransactionHistory>,
    viewModel: HomeViewModel,
    navController: NavController
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Recent Transactions", style = BodyMediumBold, color = G700)
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "View all",
                    style = BodyMediumBold,
                    color = G200,
                    modifier = modifier.clickable {
                        navController.navigate(TRANSACTIONS_HISTORY)
                    })
            }

        }
        Spacer(modifier = modifier.height(8.dp))
        LazyColumn(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color = G0)
        ) {
            itemsIndexed(transactions) { index, transaction ->
                if (transaction.status != "failed") {
                    TransactionListItem(transaction = transaction, viewModel = viewModel)
                    if (transactions.size - 1 != index)
                        HorizontalDivider(color = G40)
                }
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionListItem(
    modifier: Modifier = Modifier,
    transaction: TransactionHistory,
    viewModel: HomeViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(color = G0)
            .height(77.dp)
            .padding(8.dp)

    ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(P50)

        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_visa),
                contentDescription = "Master Card Icon",
                modifier = modifier
            )
        }
        Spacer(modifier = modifier.padding(8.dp))
        Column(
            modifier = modifier
                .width(171.dp)
                .height(57.dp)
        ) {
            Text(text = transaction.name, style = AppTextStyleSelected, color = G900)
            Text(
                text = "${transaction.cardType} - ${transaction.cardNumber}",
                style = CardDetailsTextStyle,
                color = G700
            )
            Text(
                text = viewModel.formatDateTime(transaction.transactionDate),
                style = CardDetailsTextStyle,
                color = G700
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$" + transaction.amount.toString(),
            style = BodyMediumBold,
            color = PrimaryColor,
            modifier = modifier
                .padding(top = 16.dp)
                .padding(end = 8.dp)
                .align(Alignment.Top)
        )


    }
}
