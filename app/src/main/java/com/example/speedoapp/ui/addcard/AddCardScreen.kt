package com.example.speedoapp.ui.addcard

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedoapp.navigation.AppRoutes
import com.example.speedoapp.ui.common.Data
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.theme.SubTitleTextStyle
import com.example.speedoapp.ui.theme.TitleTextStyle

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(navController:NavController,modifier: Modifier = Modifier, cardViewModel: AddCardViewModel= viewModel()) {
    val cardInfo by cardViewModel.cardInfo.collectAsState()
    val context= LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Add Card", style = SubTitleTextStyle)
            })
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .imePadding()
                .verticalScroll(rememberScrollState())

        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Sign on your Speedo Transfer Account",
                style = TitleTextStyle,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(22.dp))

            Data(labelText = "Card Holder name", cardInfo = cardInfo.cardHolder, onValueChange = { cardViewModel.updateCardholderName(it)})
            Data(labelText = "Card NO", cardInfo =cardInfo.cardNo, onValueChange = { cardViewModel.updateCardNumber(it) } )
            Row (modifier = Modifier.fillMaxSize()){
                Data(labelText = "CVV",
                    cardInfo = cardInfo.cvv,
                    onValueChange = { cardViewModel.updateCVV(it)},
                    modifier = Modifier.weight(1f)
                )
                Data(labelText = "MM\\YY",
                    cardInfo = cardInfo.expiryDate,
                    onValueChange = { cardViewModel.updateExpiryDate(it)},
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(onClick = {
                val result =cardViewModel.submitCard(cardInfo)
                if (result){
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                    navController.navigate(AppRoutes.LOADING_ROUTE)
                }
                else{
                    Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();}
            }, buttonText = "Sign on")
            PrimaryButton(onClick = { navController.navigate(AppRoutes.MY_CARDS)
                }, buttonText = "my cards")

        }
    }
}

