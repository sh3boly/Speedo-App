package com.example.speedoapp.ui.addcard

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speedoapp.ui.common.DataField
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.theme.AlertColor
import com.example.speedoapp.ui.theme.AppTextStyle
import com.example.speedoapp.ui.theme.SubTitleTextStyle
import com.example.speedoapp.ui.theme.TitleTextStyle

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(modifier: Modifier = Modifier, cardViewModel: AddCardViewModel= viewModel()) {
    val cardInfo by cardViewModel.cardInfo.collectAsState()

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

            //to do: make text and outlined text field one composable function that can be reused instead of duplicating code
            Text(text = "Card Holder name", modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp))
            OutlinedTextField(
                value = cardInfo.cardHolder,
                onValueChange = { cardViewModel.updateCardholderName(cardInfo.cardHolder) },
                placeholder = { Text(text = "Enter your name", style = AppTextStyle) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                //label = { Text(text = "Card Holder Name")},
                modifier = modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = cardInfo.cardNo,
                onValueChange = { cardViewModel.updateCardNumber(cardInfo.cardNo) },
                placeholder = { Text(text = "Enter your card NO", style = AppTextStyle) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(onClick = { /*TODO*/
                //submit fun and send OTP function
            }, buttonText = "Sign on")

        }
    }
}


@Composable
fun TextField(label: @Composable () -> Unit, singleLine: Boolean, modifier: Modifier) {

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AddCardScreenPreview(){
        val cardViewModel: AddCardViewModel = viewModel()
        AddCardScreen(cardViewModel = cardViewModel)
        LaunchedEffect(Unit) {
            // Simulate some initial state for the DataFlow
            //cardViewModel._cardInfo.value = CardInfo("m", "1000000000000000", "10\\10", "123", isLoading = false)
        }
    }