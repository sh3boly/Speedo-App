package com.example.speedoapp.ui.tranfer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.speedoapp.R
import com.example.speedoapp.navigation.AppRoutes.PAYMENT_TRANSFER
import com.example.speedoapp.ui.common.AccountCard
import com.example.speedoapp.ui.common.MenuAppBar
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.common.SecondaryButton
import com.example.speedoapp.ui.common.Stepper
import com.example.speedoapp.ui.common.TopBar
import com.example.speedoapp.ui.theme.BodyMedium
import com.example.speedoapp.ui.theme.BodyMediumBold
import com.example.speedoapp.ui.theme.G40
import com.example.speedoapp.ui.theme.G500
import com.example.speedoapp.ui.theme.G900
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.RedYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyleBold

@Composable
fun ConfirmScreen(
    navController: NavController,
    viewModel: AmountScreenViewModel,
    modifier: Modifier = Modifier
) {
    val transferData by viewModel.transferData.collectAsState()
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(OffYellowColor, RedYellowColor)
                )
            )
    ) {
        Scaffold(
            bottomBar = {
                MenuAppBar(currentScreen = "transfer", navController = navController)
            },
            containerColor = Color.Transparent,
            topBar = {
                TopBar(
                    title = stringResource(R.string.transfer),
                    navigationIcon = true,
                    color = Color.Transparent,
                    onNavigationIconClick = {
                        navController.popBackStack()
                    },
                    actions = false,
                    onActionsClick = {},
                    actionsText = ""
                )
            },
        ) { innerPadding ->
            Column(
                modifier = modifier
                    .imePadding()
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Stepper(2)
                Spacer(modifier = modifier.height(24.35.dp))
                Text(
                    text = "${transferData.from.amount} ${transferData.from.currency}",
                    style = SubTitleTextStyleBold,
                    color = G900,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.transfer_amount),
                    style = BodyMedium,
                    color = G500,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = modifier.height(24.dp))
                Row(
                    modifier = modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.total_amount),
                        style = BodyMediumBold,
                        color = G900
                    )
                    Text(
                        text = "${transferData.to.amount} ${transferData.to.currency}",
                        style = BodyMedium,
                        color = G500,
                    )
                }
                Spacer(modifier = modifier.height(16.dp))
                HorizontalDivider(color = G40)
                Spacer(modifier = modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        AccountCard(
                            identifier = 0,
                            identifierText = "From",
                            cardHolder = "Asmaa Dosuky",
                            cardNumber = "7890"
                        )
                        Spacer(modifier = Modifier.height(11.dp))
                        AccountCard(
                            identifier = 0,
                            identifierText = "To",
                            cardHolder = transferData.recipient.name,
                            cardNumber = transferData.recipient.accountNumber.takeLast(4)
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.transaction_image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(44.dp)
                            .align(Alignment.Center),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = modifier.height(32.dp))
                PrimaryButton(
                    onClick = {

                        viewModel.confirmTransfer(context)
                        navController.navigate(PAYMENT_TRANSFER)
                    },
                    buttonText = stringResource(R.string.confirm)
                )
                Spacer(modifier = modifier.height(16.dp))
                SecondaryButton(
                    onClick = { navController.popBackStack() },
                    buttonText = stringResource(R.string.previous)
                )
            }
        }
    }
}