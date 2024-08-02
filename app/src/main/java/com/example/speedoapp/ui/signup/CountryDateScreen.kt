package com.example.speedoapp.ui.signup


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.speedoapp.R
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.theme.AppTextStyle
import com.example.speedoapp.ui.theme.TitleTextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedoapp.constants.Constants.EMAIL
import com.example.speedoapp.constants.Constants.NAME
import com.example.speedoapp.constants.Constants.PASSWORD
import com.example.speedoapp.ui.common.ClickableDataField
import com.example.speedoapp.ui.common.DatePickerChooser
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDateScreen(
    navController: NavController,
    name: String,
    email: String,
    password: String,
    modifier: Modifier = Modifier
) {
    Log.d("trace", " The name, email and passwords are : $NAME, $EMAIL, $PASSWORD")
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back Icon",
                    modifier = Modifier.clickable { navController.popBackStack() }
                )
            })
        }
    ) {
        contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Speedo Transfer", style = TitleTextStyle)
            Spacer(modifier = Modifier.height(66.86.dp))
            Text(text = "Welcome to Banque Misr!", style = TitleTextStyle)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Let's Complete your Profile", style = AppTextStyle)
            Spacer(modifier = Modifier.height(32.dp))
            CountryPicker()
            Spacer(modifier = Modifier.height(8.dp))
            DatePicker()
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(onClick = { }, buttonText = "Continue")
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryPicker(modifier: Modifier = Modifier, viewModel: CountryDateViewModel = viewModel()) {
    val countries by viewModel.countries.collectAsState()
    val bottomSheetState = rememberModalBottomSheetState()
    var selectedCountry by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }

    ClickableDataField(
        value = selectedCountry,
        onValueChange = {},
        label = "Country",
        image = R.drawable.ic_dropdown,
        onClick = {
            showBottomSheet = true
        }
    )
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = bottomSheetState,
            content = {
                LazyColumn {
                    items(countries) { country ->
                        Text(
                            text = country,
                            style = AppTextStyle,
                            color = Color(0xFF706E6C),
                            modifier = Modifier
                                .clickable {
                                    selectedCountry = country
                                    showBottomSheet = false
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker() {
    var isDatePickerShown by remember { mutableStateOf(false) }
    var dateChoosen by remember {
        mutableStateOf("DD/MM/YYYY")
    }
    var year by remember { mutableIntStateOf(0) }
    var month by remember { mutableIntStateOf(0) }
    var day by remember { mutableIntStateOf(0) }

    ClickableDataField(
        value = dateChoosen,
        onValueChange = {},
        label = "Date Of Birth",
        image = R.drawable.ic_date,
        onClick = {
            isDatePickerShown = true
        }
    )
    if (isDatePickerShown) {
        DatePickerChooser(onConfirm = { dateState ->
            val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            val c = Calendar.getInstance()
            //Ex: time in millis = 1720483200000
            c.timeInMillis = dateState.selectedDateMillis ?: 0L
            //Ex: c.time = Tue Jul 09 03:00:00 GMT+03:00 2024 --> date formatter = 09-07-2024
            dateChoosen = dateFormatter.format(c.time)
            year =
                SimpleDateFormat("yyyy", Locale.US).format(dateFormatter.parse(dateChoosen)!!)
                    .toInt()
            month =
                SimpleDateFormat("MM", Locale.US).format(dateFormatter.parse(dateChoosen)!!)
                    .toInt()
            day =
                SimpleDateFormat("dd", Locale.US).format(dateFormatter.parse(dateChoosen)!!)
                    .toInt()
            isDatePickerShown = false
        }, onDismiss = { isDatePickerShown = false })
    }
}
