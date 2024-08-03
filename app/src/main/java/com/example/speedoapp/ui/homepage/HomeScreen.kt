package com.example.speedoapp.ui.homepage


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
import com.example.speedoapp.navigation.AppRoutes.AMOUNT_TRANSFER

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {


//    val balance by viewModel.balance.collectAsState()
//    val transactions by viewModel.transactions.collectAsState()
    val name by viewModel.name.collectAsState()
    Log.d("trace", "The name is : $name")
//
//    val hasError by viewModel.hasError.collectAsState()
//    if (hasError > 0)
//        Toast.makeText(LocalContext.current, "Check your connection", Toast.LENGTH_SHORT).show()
    var transactions = mutableListOf<Transaction>()
    transactions.add(Transaction("Ahmed Hamdy", "Recieved", 1000f, "12/12/2024"))
    transactions.add(Transaction("Ahmed Tarek", "Recieved", 1500f, "24/04/2024"))
    //val name = "Asmaa Desouky"
    Scaffold(
        bottomBar = { MenuAppBar(currentScreen = "home", navController = navController) }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 15.dp, top = 78.dp)
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(

                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                UserIcon(initials = viewModel.getInitials(name))

                Spacer(modifier = Modifier.padding(8.dp))

                Column() {
                    Text(text = "Welcome back,", style = AppTextStyle, color = PrimaryColor)
                    Text(text = name, style = AppTextStyle, color = G900)
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "Notification bell Icon"
                    )
                }

            }
            Spacer(modifier = modifier.padding(15.dp))
            BalanceCard()
            Spacer(modifier = modifier.padding(16.dp))
            Services(navController = navController)
            Spacer(modifier = modifier.padding(16.dp))
            RecentTransaction(transactions = transactions)

        }
    }
}

@Composable
fun BalanceCard(modifier: Modifier = Modifier) {
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
            Text(text = "$2,85,865.20", style = BodyMedium, color = G0)
        }

    }
}

@Composable
fun Services(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = Modifier
            .width(343.dp)
            .height(141.dp)

    ) {
        Box(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Text(text = "Services", style = BodyMedium, color = G700)
        }
        Spacer(modifier = modifier.padding(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    start = 12.dp, end = 15.dp, top = 0.dp, bottom = 8.dp
                )

        ) {
            IconWithText(
                icon = R.drawable.ic_transfer,
                text = "Transfer",
                onClick = { navController.navigate(AMOUNT_TRANSFER) })
            Spacer(modifier = Modifier.padding(horizontal = 16.dp))

            IconWithText(icon = R.drawable.ic_history, text = "Transactions", onClick = {})
            Spacer(modifier = Modifier.padding(horizontal = 16.dp))

            IconWithText(icon = R.drawable.ic_cards, text = "Cards", onClick = {})
            Spacer(modifier = Modifier.padding(horizontal = 16.dp))

            IconWithText(icon = R.drawable.ic_account, text = "Account", onClick = {})
            Spacer(modifier = Modifier.padding(horizontal = 16.dp))


        }

    }
}

@Composable
fun RecentTransaction(modifier: Modifier = Modifier, transactions: List<Transaction>) {
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
                Text(text = "View all", style = BodyMediumBold, color = G200)
            }

        }
        LazyColumn {
            items(transactions) { transaction ->
                TransactionListItem(transaction = transaction)
            }
        }

    }
}

@Composable
fun TransactionListItem(modifier: Modifier = Modifier, transaction: Transaction) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(77.dp)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_visa),
            contentDescription = "Master Card Icon"
        )
        Spacer(modifier = modifier.padding(8.dp))
        Column(
            modifier = modifier
                .width(171.dp)
                .height(57.dp)
        ) {
            Text(text = transaction.name, style = AppTextStyleSelected, color = G900)
            Text(text = "Visa . Master Card . 1234", style = CardDetailsTextStyle, color = G700)
            Text(
                text = transaction.date + " - " + transaction.type,
                style = CardDetailsTextStyle,
                color = G700
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "$" + transaction.amount.toString(),
            style = BodyMediumBold,
            color = PrimaryColor,
            modifier = modifier.align(Alignment.Top)
        )


    }
}

