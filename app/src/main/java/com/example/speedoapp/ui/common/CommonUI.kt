package com.example.speedoapp.ui.common

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.speedoapp.R
import com.example.speedoapp.model.Currency
import com.example.speedoapp.navigation.AppRoutes
import com.example.speedoapp.ui.theme.AlertColor
import com.example.speedoapp.ui.theme.AppNumbersStyle
import com.example.speedoapp.ui.theme.AppTextStyle
import com.example.speedoapp.ui.theme.AppTextStyleSelected
import com.example.speedoapp.ui.theme.BodyMedium
import com.example.speedoapp.ui.theme.BodyMediumBold
import com.example.speedoapp.ui.theme.ButtonMedium
import com.example.speedoapp.ui.theme.BoldNavTextStyle
import com.example.speedoapp.ui.theme.ButtonTextColor
import com.example.speedoapp.ui.theme.DisabledColor
import com.example.speedoapp.ui.theme.G0
import com.example.speedoapp.ui.theme.G100
import com.example.speedoapp.ui.theme.G200
import com.example.speedoapp.ui.theme.G40
import com.example.speedoapp.ui.theme.G40
import com.example.speedoapp.ui.theme.G70
import com.example.speedoapp.ui.theme.G700
import com.example.speedoapp.ui.theme.G900
import com.example.speedoapp.ui.theme.P300
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.P50
import com.example.speedoapp.ui.theme.PrimaryColor
import com.example.speedoapp.ui.theme.RedYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle
import com.example.speedoapp.ui.theme.SubTitleTextStyleBold
import com.example.speedoapp.ui.tranfer.AmountScreenViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    buttonText: String
) {
    Button(
        onClick = onClick,
        colors = ButtonColors(
            containerColor = PrimaryColor,
            contentColor = ButtonTextColor,
            disabledContainerColor = DisabledColor,
            disabledContentColor = ButtonTextColor
        ),
        enabled = isEnabled,
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(51.dp)
    ) {
        Text(text = buttonText, style = ButtonMedium, color = G0)
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    buttonText: String
) {
    Button(
        onClick = onClick,
        border = BorderStroke(width = 1.5.dp, color = PrimaryColor),
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = PrimaryColor,
            disabledContainerColor = DisabledColor,
            disabledContentColor = ButtonTextColor
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(51.dp)
    ) {
        Text(text = buttonText, style = ButtonMedium, color = PrimaryColor)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: String? = null,
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        Text(text = "Password", style = AppTextStyle)
        Spacer(modifier = modifier.height(8.dp))

        OutlinedTextField(
            colors = outlinedTextFieldColors(
                containerColor = G0,
                focusedBorderColor = PrimaryColor,
                unfocusedBorderColor = G70,
                cursorColor = colorResource(id = R.color.black)
            ),
            isError = isError != null,
            visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = label, style = AppTextStyle, color = G70)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (isError == null) {
                    if (passwordVisible) {
                        R.drawable.ic_hidden_typing
                    } else
                        if (value.isEmpty())
                            R.drawable.ic_shown
                        else
                            R.drawable.ic_shown_typing
                } else {
                    R.drawable.ic_shown_error
                }
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Image(painterResource(id = image), description, Modifier.size(24.dp))
                }
            },
            modifier = modifier.fillMaxWidth()
        )
        if (isError != null)
            Text(
                text = isError,
                style = AppTextStyle,
                color = AlertColor
            )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataField(
    modifier: Modifier = Modifier,
    isError: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    @DrawableRes image: Int? = null,
    @DrawableRes typingImage: Int? = null,
    imageDescription: String = "",
    type: KeyboardType,
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = label,
            style = BodyMedium,
            color = G700,
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = modifier.height(8.dp))
        OutlinedTextField(
            shape = RoundedCornerShape(6.dp),
            isError = isError != null,
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = "Enter your $label", style = AppTextStyle, color = G70)
            },
            keyboardOptions = KeyboardOptions(keyboardType = type),
            trailingIcon = {
                if (typingImage != null && value.isNotEmpty()) {
                    Image(painterResource(id = typingImage), imageDescription, Modifier.size(24.dp))
                } else if (image != null) {
                    Image(painterResource(id = image), imageDescription, Modifier.size(24.dp))
                }
            },
            colors = outlinedTextFieldColors(
                containerColor = G0,
                focusedBorderColor = PrimaryColor,
                unfocusedBorderColor = G70,
                cursorColor = colorResource(id = R.color.black)
            ),
            modifier = modifier
                .fillMaxWidth()
        )
        if (isError != null)
            Text(
                text = isError,
                style = AppTextStyle,
                color = AlertColor
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navigationIcon: Boolean,
    color: Color,
    onNavigationIconClick: () -> Unit = {},
    actions: Boolean,
    onActionsClick: () -> Unit = {},
    actionsText: String
) {
    CenterAlignedTopAppBar(title = {
        Text(
            title, style = SubTitleTextStyle
        )
    }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = color // Set the background color here
    ), navigationIcon = {
        if (navigationIcon) {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
        }
    }, actions = {
        if (actions) {
            Text(text = actionsText,
                style = BodyMediumBold,
                color = G100,
                modifier = Modifier
                    .clickable { onActionsClick() }
                    .padding(end = 16.dp))
        }
    })
}

@Composable
fun Stepper(currentStep: Int) {
    val steps = listOf(
        stringResource(R.string.amount),
        stringResource(R.string.confirmation),
        stringResource(R.string.payment)
    )
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 17.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            steps.forEachIndexed { index, _ ->
                StepItem(
                    stepNumber = index + 1, isCurrent = currentStep > index
                )
                if (index != steps.size - 1) {
                    LineBetweenSteps(isActive = (currentStep > index && (currentStep == 1 || currentStep == 3)) || (currentStep > index + 1 && currentStep == 2))
                }
            }
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 9.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {//This row for the titles
            steps.forEachIndexed { index, step ->
                Text(
                    text = step,
                    textAlign = TextAlign.Center,
                    style = if (currentStep > index) AppTextStyleSelected else AppTextStyle,
                    color = if (currentStep > index) G900 else G100
                )
            }
        }
    }

}

@Composable
fun StepItem(stepNumber: Int, isCurrent: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(35.77.dp)
                .clip(CircleShape)
                .background(
                    Color.White
                )
                .border(
                    width = 2.24.dp,
                    color = if (isCurrent) PrimaryColor else DisabledColor,
                    shape = CircleShape
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "0$stepNumber",
                color = if (isCurrent) PrimaryColor else DisabledColor,
                style = AppNumbersStyle
            )
        }

    }
}

@Composable
fun LineBetweenSteps(isActive: Boolean) {
    Box(
        modifier = Modifier
            .height(1.49.dp)
            .width(85.dp)
            .background(
                if (isActive) PrimaryColor
                else DisabledColor
            )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClickableDataField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    @DrawableRes image: Int,
    imageDescription: String = "",
    onClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(text = label, style = AppTextStyle)
        Spacer(modifier = modifier.height(8.dp))
        OutlinedTextField(colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
        ), enabled = false, value = value, onValueChange = onValueChange, placeholder = {
            if (label == "Country") Text(text = "Select your $label", style = AppTextStyle)
            else Text(text = "DD/MM/YYYY", style = AppTextStyle)
        }, trailingIcon = {
            Image(painterResource(id = image), imageDescription, Modifier.size(24.dp))


        }, modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)


        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerChooser(onConfirm: (DatePickerState) -> Unit, onDismiss: () -> Unit) {

    /*
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    //Months are zero base (e.g. January = 0 & December = 11)
    val month = c.get(Calendar.MONTH) + 1
    val day = c.get(Calendar.DAY_OF_MONTH)
    val date = "$year/$month/$day"
    val dateFormatter = SimpleDateFormat("yyyy/MM/dd", Locale.US)
    val parsed = dateFormatter.parse(date)?.time ?: 0L
    */

    val datePickerState = rememberDatePickerState(
        //initialSelectedDateMillis = parsed,
        //yearRange = (2024..2025),
    )

    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onConfirm(datePickerState) }) {
                Text(text = "Ok")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text(text = "Cancel") }
        },
        text = { DatePicker(state = datePickerState) },
    )
}

@Composable
fun CurrenciesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    identifier: Int,
    onCurrencySelected: (Currency) -> Unit,
    viewModel: AmountScreenViewModel = viewModel()
) {
    Log.d("HELLO", viewModel.imageTo.collectAsState().value)
    var selectedCurrencyIndex by rememberSaveable { mutableStateOf<Int?>(null) }
    val currencies by viewModel.currencies.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(G0, RedYellowColor)
                )
            )
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopBar(
                    title = stringResource(R.string.select_currency),
                    navigationIcon = true,
                    color = Color.Transparent,
                    onNavigationIconClick = {
                        navController.popBackStack()
                    },
                    actions = true,
                    onActionsClick = {
                        navController.popBackStack()
                    },
                    actionsText = stringResource(R.string.cancel)
                )
            },
        ) { innerPadding ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 42.dp)
                    .padding(innerPadding),

                ) {
                LazyColumn {
                    itemsIndexed(currencies) { index, currency ->
                        ListItem(
                            modifier = modifier.clickable { selectedCurrencyIndex = index },
                            leadingContent = {
                                AsyncImage(
                                    model = currency.imageUrl,
                                    contentDescription = stringResource(id = R.string.country_icon),
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                )
                            },
                            headlineContent = {
                                Text(
                                    text = currency.name,
                                    style = BodyMediumBold,
                                    color = G100
                                )
                            },
                            trailingContent = {
                                if (selectedCurrencyIndex == index) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = stringResource(R.string.selected),
                                        tint = PrimaryColor
                                    )
                                }
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = if (selectedCurrencyIndex == index) P50 else Color.Transparent
                            )
                        )
                        if (index != currencies.lastIndex) {
                            HorizontalDivider(color = G40)
                        }
                    }
                }
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = modifier
                        .fillMaxSize()
                        .padding(bottom = 77.dp)
                ) {
                    PrimaryButton(
                        onClick = {
                            if (selectedCurrencyIndex != null) {
                                val selectedCurrency = currencies[selectedCurrencyIndex!!]
                                onCurrencySelected(selectedCurrency)
                            }
                            navController.popBackStack()
                        },
                        buttonText = stringResource(R.string.select),
                        isEnabled = selectedCurrencyIndex != null
                    )
                }
            }
        }
    }
}

@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    identifier: Int,
    identifierText: String? = null,
    cardHolder: String,
    cardNumber: String,
) {
    Card(
        colors = CardDefaults.cardColors(P50),
        modifier = Modifier
            .height(if (identifier == 0) 136.dp else 93.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(ButtonTextColor)
                    .align(Alignment.CenterVertically)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bank),
                    contentDescription = stringResource(R.string.bank_icon),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(32.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .padding(start = 32.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                if (identifier == 0) {
                    Text(
                        text = identifierText!!,
                        style = BodyMediumBold,
                        color = PrimaryColor,
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                }
                Text(
                    text = cardHolder,
                    style = SubTitleTextStyleBold,
                    color = G900,
                )
                Spacer(modifier = Modifier.padding(6.dp))
                Text(
                    text = "Account xxxx$cardNumber",
                    style = BodyMedium,
                    color = G100
                )
            }
        }
    }
}

@Composable
fun UserIcon(modifier: Modifier = Modifier, initials: String) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(G40, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials, style = SubTitleTextStyle, color = G100
        )

    }

}

@Composable
fun IconWithText(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)

    ) {
        Image(painter = painterResource(id = icon), contentDescription = text)
        Spacer(modifier = modifier.padding(4.dp))
        Text(text = text, style = AppTextStyleSelected, color = G700)
    }
}
@Composable
fun NavItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit,
    color: Color = G200
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)

    ) {
        Image(painter = painterResource(id = icon), contentDescription = text)
        Spacer(modifier = modifier.padding(4.dp))
        Text(text = text, style = BoldNavTextStyle, color = color)
    }
}

@Composable
fun MenuAppBar(modifier: Modifier = Modifier, currentScreen: String) {
    BottomAppBar(actions = {
        if (currentScreen == "home")
            NavItem(
                icon = R.drawable.ic_selected_home,
                text = "Home",
                onClick = {},
                color = P300)
        else
            NavItem(
                icon = R.drawable.ic_home,
                text = "Home",
                onClick = {})

        Spacer(modifier = modifier.padding(10.dp))

        if (currentScreen == "transfer")
            NavItem(
                icon = R.drawable.ic_selected_transfer,
                text = "Transfer",
                onClick = {},
                color = P300)
        else
            NavItem(
                icon = R.drawable.ic_normal_transfer,
                text = "Transfer",
                onClick = {})

        Spacer(modifier = modifier.padding(10.dp))


        if (currentScreen == "transactions")
            NavItem(
                icon = R.drawable.ic_selected_history,
                text = "Transactions",
                onClick = {},
                color = P300)
        else
            NavItem(
                icon = R.drawable.ic_normal_history,
                text = "Transactions",
                onClick = {})

        Spacer(modifier = modifier.padding(10.dp))

        if (currentScreen == "mycards")
            NavItem(
                icon = R.drawable.ic_selected_mycard,
                text = "My Cards",
                onClick = {},
                color = P300)
        else
            NavItem(
                icon = R.drawable.ic_mycard,
                text = "My Cards",
                onClick = {})

        Spacer(modifier = modifier.padding(10.dp))

        if (currentScreen == "more")
            NavItem(
                icon = R.drawable.ic_selected_more,
                text = "More",
                color = P300,
                onClick = {})
        else
            NavItem(
                icon = R.drawable.ic_more,
                text = "More",
                onClick = {})

    },

        modifier = modifier.padding(horizontal = 35.5.dp).fillMaxWidth()

)
}

@Preview(showBackground = true)
@Composable
fun ComposablePreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Stepper(currentStep = 3)
    }
}
