package com.example.speedoapp.ui.tranfer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedoapp.ui.common.TopBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.speedoapp.R
import com.example.speedoapp.ui.common.DataField
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.common.Stepper
import com.example.speedoapp.ui.theme.AppTextStyle
import com.example.speedoapp.ui.theme.AppTextStyleSelected
import com.example.speedoapp.ui.theme.BodyMedium
import com.example.speedoapp.ui.theme.BodyMediumBold
import com.example.speedoapp.ui.theme.ButtonTextColor
import com.example.speedoapp.ui.theme.DisabledColor
import com.example.speedoapp.ui.theme.G0
import com.example.speedoapp.ui.theme.G100
import com.example.speedoapp.ui.theme.G40
import com.example.speedoapp.ui.theme.G700
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.PrimaryColor
import com.example.speedoapp.ui.theme.RedYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyleBold
import com.example.speedoapp.ui.theme.SubTitleTextStyleLight
import kotlinx.coroutines.launch
import java.math.BigDecimal

data class Country(val code: String, val name: String, val icon: ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountScreen(modifier: Modifier = Modifier) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
        sheetPeekHeight = 0.dp,
        sheetContainerColor = G0,
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 32.dp)
                    .fillMaxWidth()
                    .height(400.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite),
                        tint = PrimaryColor,
                        contentDescription = stringResource(R.string.show_favourites),
                        modifier = modifier
                            .size(24.dp)
                    )
                    Spacer(modifier = modifier.width(2.dp))
                    Text(
                        text = stringResource(R.string.favourite),
                        style = SubTitleTextStyleLight,
                        color = PrimaryColor,
                        textAlign = TextAlign.End
                    )
                }
            }

        }) {
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
                containerColor = Color.Transparent,
                topBar = {
                    TopBar(title = stringResource(R.string.transfer),
                        navigationIcon = true,
                        color = Color.Transparent,
                        onNavigationIconClick = {})
                },
            ) { innerPadding ->
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                        .imePadding()
                ) {
                    Stepper(1)
                    Spacer(modifier = modifier.height(19.35.dp))
                    Text(
                        text = stringResource(R.string.how_much_are_you_sending),
                        textAlign = TextAlign.Start,
                        style = SubTitleTextStyleBold
                    )
                    Spacer(modifier = modifier.height(24.dp))
                    CurrencyEquivalence()
                    Spacer(modifier = modifier.height(24.dp))
                    Row(
                        modifier = modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.recipient_information),
                            style = BodyMediumBold,
                            textAlign = TextAlign.Start,
                            color = G700
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.clickable {
                                scope.launch { scaffoldState.bottomSheetState.expand() }
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite),
                                tint = PrimaryColor,
                                contentDescription = stringResource(R.string.show_favourites),
                                modifier = modifier
                                    .size(20.dp)
                            )
                            Spacer(modifier = modifier.width(2.dp))
                            Text(
                                text = stringResource(R.string.favourite),
                                style = AppTextStyleSelected,
                                color = PrimaryColor,
                                textAlign = TextAlign.End
                            )
                            Spacer(modifier = modifier.width(2.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = stringResource(R.string.show_favourites),
                                tint = PrimaryColor,
                                modifier = modifier
                                    .size(16.dp)
                            )

                        }
                    }
                    Spacer(modifier = modifier.height(16.dp))
                    DataField(
                        value = "",
                        onValueChange = {},
                        label = stringResource(id = R.string.recipient_name),
                        image = null,
                        typingImage = null,
                        type = KeyboardType.Text
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    DataField(
                        value = "",
                        onValueChange = {},
                        label = stringResource(id = R.string.recipient_account),
                        image = null,
                        typingImage = null,
                        type = KeyboardType.Text
                    )
                    Spacer(modifier = modifier.height(32.dp))
                    PrimaryButton(
                        onClick = { /*TODO*/ },
                        buttonText = stringResource(R.string.continue_button),
                        isEnabled = false
                    )
                }
            }
        }
    }
}

@Composable
fun CurrencyEquivalence(modifier: Modifier = Modifier) {
    var toBeSent by rememberSaveable { mutableStateOf(BigDecimal("1000")) }
    var receiverGets by rememberSaveable { mutableStateOf(BigDecimal("48332.50")) }
    val selectedCurrencyFrom =
        remember { mutableStateOf(Country("USD", "US Dollar", Icons.Default.AccountCircle)) }
    val selectedCurrencyTo =
        remember { mutableStateOf(Country("EGP", "EGP Pound", Icons.Default.AccountCircle)) }
    val countries = listOf(
        Country("USD", "US Dollar", Icons.Default.AccountCircle),
        Country("EUR", "Euro", Icons.Default.AccountCircle),
        Country("EUR", "Euro", Icons.Default.AccountCircle),
        Country("EUR", "Euro", Icons.Default.AccountCircle),
        Country("EUR", "Euro", Icons.Default.AccountCircle),
        Country("EUR", "Euro", Icons.Default.AccountCircle),
    )
    Column(
        modifier = modifier.fillMaxSize()

    ) {
        Text(
            text = stringResource(R.string.choose_currency),
            textAlign = TextAlign.Start,
            style = BodyMediumBold,
            color = G700
        )
        Spacer(modifier = modifier.height(16.dp))
        Box(
            modifier = modifier
                .height(298.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(G0)
                .align(Alignment.CenterHorizontally)
        ) {
            Column(
                modifier = modifier
                    .padding(horizontal = 8.dp)
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "1 USD = 48.4220 EGP", style = BodyMediumBold, color = G700
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = "Rate guaranteed (2h)", style = AppTextStyleSelected, color = G100
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.you_send),
                    style = BodyMedium,
                    color = G700
                )
                Spacer(modifier = modifier.height(15.dp))
                ValueCurrencyItem(
                    value = toBeSent,
                    onValueChange = { toBeSent = it },
                    selectedCountry = selectedCurrencyFrom,
                    countries = countries
                )
                Spacer(modifier = modifier.height(16.dp))
                HorizontalDivider(
                    color = G40,
                    thickness = 1.dp,
                    modifier = modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = modifier.height(15.dp))
                Text(
                    text = stringResource(R.string.recipient_gets),
                    style = BodyMedium,
                    color = G700
                )
                Spacer(modifier = modifier.height(15.dp))
                ValueCurrencyItem(
                    value = receiverGets,
                    onValueChange = { receiverGets = it },
                    selectedCountry = selectedCurrencyTo,
                    countries = countries
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValueCurrencyItem(
    countries: List<Country>,
    selectedCountry: MutableState<Country>,
    value: BigDecimal,
    onValueChange: (BigDecimal) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDropdown by rememberSaveable { mutableStateOf(false) }
    var isFocused by rememberSaveable { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .clickable { showDropdown = !showDropdown }
            ) {
                Icon(
                    imageVector = selectedCountry.value.icon,
                    contentDescription = stringResource(R.string.country_icon),
                    modifier = modifier
                        .padding(start = 8.dp)
                        .size(24.dp)
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    text = selectedCountry.value.code,
                    style = SubTitleTextStyleBold,
                    color = PrimaryColor
                )
                Spacer(modifier = modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = DisabledColor,
                    modifier = modifier
                        .padding(end = 8.dp)
                        .size(24.dp)
                )
            }
            DropdownMenu(
                expanded = showDropdown,
                onDismissRequest = { showDropdown = !showDropdown },
                modifier = Modifier.background(ButtonTextColor)
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text(text = country.code, style = AppTextStyle) },
                        onClick = { selectedCountry.value = country },
                        enabled = true

                    )
                }
            }
        }

        Row {
            Spacer(modifier = modifier.width(49.28.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(8.dp),
                value = value.toString(),
                textStyle = SubTitleTextStyleBold,
                onValueChange = {
                    onValueChange(it.toBigDecimalOrNull() ?: BigDecimal.ZERO)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = modifier
                    .height(55.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                singleLine = true,
                colors = outlinedTextFieldColors(
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = G700,
                    cursorColor = colorResource(id = R.color.black)
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmountScreenPreview() {
    AmountScreen()
}