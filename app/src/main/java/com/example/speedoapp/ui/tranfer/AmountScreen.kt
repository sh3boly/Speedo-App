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
import androidx.compose.ui.unit.dp
import com.example.speedoapp.ui.common.TopBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.speedoapp.R
import com.example.speedoapp.model.TransferData
import com.example.speedoapp.navigation.AppRoutes.CONFIRM_TRANSFER
import com.example.speedoapp.navigation.AppRoutes.SELECT_CURRENCY
import com.example.speedoapp.ui.common.AccountCard
import com.example.speedoapp.ui.common.DataField
import com.example.speedoapp.ui.common.MenuAppBar
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.common.Stepper
import com.example.speedoapp.ui.theme.AlertColor
import com.example.speedoapp.ui.theme.AppTextStyle
import com.example.speedoapp.ui.theme.AppTextStyleSelected
import com.example.speedoapp.ui.theme.BodyMedium
import com.example.speedoapp.ui.theme.BodyMediumBold
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AmountScreenViewModel
) {
    viewModel.getBalance()
    viewModel.getFavourites()
    val transferData by viewModel.transferData.collectAsState()
    //val hasError by viewModel.hasError.collectAsState() -> will use it to show error screen in case error
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    var isError by remember { mutableStateOf<String?>(null) }
    val favourites by viewModel.favourites.collectAsState()
    var selectedFavouriteIndex by rememberSaveable { mutableStateOf<Int?>(null) }

//    val context = LocalContext.current
//    viewModel.loadCurrenciesFromLocal(context)
    BottomSheetScaffold(sheetShape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
        sheetPeekHeight = 0.dp,
        sheetContainerColor = G0,
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = modifier
                    .imePadding()
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 32.dp)
                    .height(400.dp),

            ) {

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite),
                        tint = PrimaryColor,
                        contentDescription = stringResource(R.string.show_favourites),
                        modifier = modifier.size(24.dp)
                    )
                    Spacer(modifier = modifier.width(2.dp))
                    Text(
                        text = stringResource(R.string.favourite),
                        style = SubTitleTextStyleLight,
                        color = PrimaryColor,
                        textAlign = TextAlign.End
                    )
                }
                Spacer(modifier = modifier.height(16.dp))
                LazyColumn {
                    itemsIndexed(favourites) { index, favourite ->
                        AccountCard(
                            identifier = 1,
                            cardHolder = favourite.recipientName,
                            cardHolderTextStyle = BodyMediumBold,
                            cardNumber = favourite.recipientAccountNumber,
                            isSelected = selectedFavouriteIndex == index, // Pass selection state
                            onCardClick = {
                                selectedFavouriteIndex = index
                                viewModel.updateRecipientName(favourite.recipientName)
                                viewModel.updateRecipientAccountNumber(favourite.recipientAccountNumber)

                                scope.launch {
                                    kotlinx.coroutines.delay(300)
                                    scaffoldState.bottomSheetState.partialExpand()
                                }
                            }
                        )
                        Spacer(modifier = modifier.height(16.dp))
                    }
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
                bottomBar = {
                    MenuAppBar(currentScreen = "transfer", navController = navController)
                },
                containerColor = Color.Transparent,
                modifier = Modifier.imePadding(),
                topBar = {
                    TopBar(
                        title = stringResource(R.string.transfer),
                        navigationIcon = true,
                        color = Color.Transparent,
                        onNavigationIconClick = {
                            viewModel.reset()
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
                    Stepper(1)
                    Spacer(modifier = modifier.height(19.35.dp))
                    Text(
                        text = stringResource(R.string.how_much_are_you_sending),
                        textAlign = TextAlign.Start,
                        style = SubTitleTextStyleBold
                    )
                    Spacer(modifier = modifier.height(24.dp))
                    CurrencyEquivalence(
                        viewModel = viewModel,
                        transferData = transferData,
                        navController = navController
                    )
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
                        Row(verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier.clickable {
                                scope.launch { scaffoldState.bottomSheetState.expand() }
                            }) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite),
                                tint = PrimaryColor,
                                contentDescription = stringResource(R.string.show_favourites),
                                modifier = modifier.size(20.dp)
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
                                modifier = modifier.size(16.dp)
                            )

                        }
                    }
                    Spacer(modifier = modifier.height(16.dp))
                    DataField(
                        value = transferData.recipient.name,
                        onValueChange = {
                            viewModel.updateRecipientName(it)
                        },
                        label = stringResource(id = R.string.recipient_name),
                        image = null,
                        typingImage = null,
                        type = KeyboardType.Text
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    DataField(
                        value = transferData.recipient.accountNumber,
                        onValueChange = {
                            viewModel.updateRecipientAccountNumber(it)
                            isError = null
                        },
                        label = stringResource(id = R.string.recipient_account_number),
                        image = null,
                        typingImage = null,
                        type = KeyboardType.Number,
                        isError = isError
                    )
                    Spacer(modifier = modifier.height(32.dp))
                    PrimaryButton(
                        onClick = {
                            isError = viewModel.accountNumberValidation()
                            if (isError == null)
                                navController.navigate(CONFIRM_TRANSFER)

                        },
                        buttonText = stringResource(R.string.continue_button),
                        isEnabled = viewModel.validateData()
                    )
                    Spacer(modifier = modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun CurrencyEquivalence(
    navController: NavController,
    viewModel: AmountScreenViewModel,
    transferData: TransferData,
    modifier: Modifier = Modifier
) {
    val imageUrlTo by viewModel.imageTo.collectAsState()
    val imageUrlFrom by viewModel.imageFrom.collectAsState()
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
                .height(316.dp)
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
                    text = "1 ${transferData.from.currency} = ${viewModel.exchangeRateText.collectAsState().value} ${transferData.to.currency}",
                    style = BodyMediumBold,
                    color = G700
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = "Rate guaranteed (2h)", style = AppTextStyleSelected, color = G100
                )
                Spacer(modifier = modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.you_send), style = BodyMedium, color = G700
                )
                Spacer(modifier = modifier.height(15.dp))
                ValueCurrencyItem(

                    onValueChange = {
                        viewModel.updateAmountFrom(it)
                    },
                    isError = viewModel.amountValidation(),
                    conCurrencyListClick = {
                        val identifier = 0
                        navController.navigate("$SELECT_CURRENCY/$identifier")
                    },
                    selectedCurrencyCode = transferData.from.currency,
                    value = transferData.from.amount,
                    imageUrl = imageUrlFrom
                )
                Spacer(modifier = modifier.height(16.dp))
                HorizontalDivider(
                    color = G40, thickness = 1.dp, modifier = modifier.padding(horizontal = 8.dp)
                )
                Spacer(modifier = modifier.height(15.dp))
                Text(
                    text = stringResource(R.string.recipient_gets), style = BodyMedium, color = G700
                )
                Spacer(modifier = modifier.height(15.dp))
                ValueCurrencyItem(
                    onValueChange = { viewModel.updateAmountTo(it) },
                    conCurrencyListClick = {
                        val identifier = 1
                        navController.navigate("$SELECT_CURRENCY/$identifier")
                    },
                    selectedCurrencyCode = transferData.to.currency,
                    value = transferData.to.amount,
                    imageUrl = imageUrlTo,
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValueCurrencyItem(
    modifier: Modifier = Modifier,
    selectedCurrencyCode: String,
    onValueChange: (String) -> Unit,
    conCurrencyListClick: () -> Unit,
    value: String,
    imageUrl: String,
    isError: String? = null,
) {
    var isFocused by rememberSaveable { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.clickable { conCurrencyListClick() }) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = stringResource(id = R.string.country_icon),
                    modifier = modifier
                        .width(24.dp)
                        .height(24.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = modifier.width(4.dp))
                Text(
                    text = selectedCurrencyCode,
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
        }

        Row {
            Spacer(modifier = modifier.width(49.28.dp))
            Column {
                OutlinedTextField(
                    shape = RoundedCornerShape(8.dp),
                    value = value,
                    textStyle = SubTitleTextStyleBold,
                    isError = isError != null,
                    onValueChange = onValueChange,
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
                    )
                )
                if (isError != null) Text(
                    text = isError, style = AppTextStyle, color = AlertColor,
                    modifier = modifier.padding(top = 4.dp)
                )
            }

        }
    }
}