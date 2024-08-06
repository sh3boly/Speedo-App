package com.example.speedoapp.ui.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
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
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
import com.example.speedoapp.navigation.AppRoutes.AMOUNT_TRANSFER
import com.example.speedoapp.navigation.AppRoutes.HOME_ROUTE
import com.example.speedoapp.navigation.AppRoutes.MORE_ROUTE
import com.example.speedoapp.navigation.AppRoutes.MY_CARDS
import com.example.speedoapp.navigation.AppRoutes.TRANSACTIONS_DETAILS
import com.example.speedoapp.navigation.AppRoutes.TRANSACTIONS_HISTORY
import com.example.speedoapp.ui.theme.AlertColor
import com.example.speedoapp.ui.theme.AppNumbersStyle
import com.example.speedoapp.ui.theme.AppTextStyle
import com.example.speedoapp.ui.theme.AppTextStyleSelected
import com.example.speedoapp.ui.theme.BodyMedium
import com.example.speedoapp.ui.theme.BodyMediumBold
import com.example.speedoapp.ui.theme.ButtonMedium
import com.example.speedoapp.ui.theme.BoldNavTextStyle
import com.example.speedoapp.ui.theme.ButtonTextColor
import com.example.speedoapp.ui.theme.CardDetailsTextStyle
import com.example.speedoapp.ui.theme.DisabledColor
import com.example.speedoapp.ui.theme.G0
import com.example.speedoapp.ui.theme.G100
import com.example.speedoapp.ui.theme.G200
import com.example.speedoapp.ui.theme.G40
import com.example.speedoapp.ui.theme.G70
import com.example.speedoapp.ui.theme.G700
import com.example.speedoapp.ui.theme.G900
import com.example.speedoapp.ui.theme.GradientEnd
import com.example.speedoapp.ui.theme.GradientStart
import com.example.speedoapp.ui.theme.HeadingTextStyle
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.P300
import com.example.speedoapp.ui.theme.P50
import com.example.speedoapp.ui.theme.P75
import com.example.speedoapp.ui.theme.PrimaryColor
import com.example.speedoapp.ui.theme.RedYellowColor
import com.example.speedoapp.ui.theme.SmallTextStyle
import com.example.speedoapp.ui.theme.SubTitleTextStyle
import com.example.speedoapp.ui.theme.SubTitleTextStyleBold
import com.example.speedoapp.ui.tranfer.AmountScreenViewModel
import kotlinx.coroutines.launch

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
                    } else if (value.isEmpty()) R.drawable.ic_shown
                    else R.drawable.ic_shown_typing
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
        if (isError != null) Text(
            text = isError, style = AppTextStyle, color = AlertColor
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
    hintText: String? = null,
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
                if (hintText == null)
                    Text(text = "Enter your $label", style = AppTextStyle, color = G70)
                else
                    Text(text = "Enter $hintText", style = AppTextStyle, color = G70)
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
                cursorColor = colorResource(id = R.color.black),
                errorContainerColor = G0
            ),
            modifier = modifier.fillMaxWidth()
        )
        if (isError != null) Text(
            text = isError, style = AppTextStyle, color = AlertColor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataFieldInt(
    modifier: Modifier = Modifier,
    isError: String? = null,
    value: Int,
    onValueChange: (Int) -> Unit,
    label: String,
    @DrawableRes image: Int? = null,
    @DrawableRes typingImage: Int? = null,
    imageDescription: String = "",
    type: KeyboardType = KeyboardType.Number,
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
            value = value.toString(), // Convert value to String for display
            onValueChange = { newValue ->
                try {
                    val intValue =
                        newValue.toIntOrNull() ?: return@OutlinedTextField // Handle invalid input
                    onValueChange(intValue)
                } catch (e: NumberFormatException) {
                    // Handle potential exception for invalid input (optional)
                }
            },
            placeholder = {
                Text(text = "Enter your $label", style = AppTextStyle, color = G70)
            },
            keyboardOptions = KeyboardOptions(),
            trailingIcon = {
                if (typingImage != null && value > 0) { // Check if value is non-zero
                    Image(painterResource(id = typingImage), imageDescription, Modifier.size(24.dp))
                } else if (image != null) {
                    Image(painterResource(id = image), imageDescription, Modifier.size(24.dp))
                }
            },
            colors = outlinedTextFieldColors(
                containerColor = G0,
                focusedBorderColor = PrimaryColor,
                unfocusedBorderColor = G70,
                cursorColor = colorResource(id = R.color.black),
                errorContainerColor = G0
            ),
            modifier = modifier.fillMaxWidth()
        )
        if (isError != null) Text(
            text = isError, style = AppTextStyle, color = AlertColor
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
fun AccountCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onCardClick: (() -> Unit)? = null,
    cardHolder: String,
    cardHolderTextStyle: TextStyle = SubTitleTextStyleBold,
    cardNumber: String,
    @DrawableRes firstImageInRow: Int? = null,
    onFirstActionClick: (() -> Unit)? = null,
    @DrawableRes secondImageInRow: Int? = null,
    onSecondActionClick: (() -> Unit)? = null,
    identifierText: String? = null,
    identifier: Int = 0,
) {
    Card(
        colors = CardDefaults.cardColors(P50),
        modifier = Modifier
            .height(if (identifier == 0) 136.dp else 93.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = if (isSelected) P75 else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onCardClick?.invoke() },
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
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
                        .fillMaxHeight(),
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
                        style = cardHolderTextStyle,
                        color = G900,
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(
                        text = "Account xxxx${cardNumber.takeLast(4)}",
                        style = BodyMedium,
                        color = G100
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                firstImageInRow?.let { imageResId ->
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = stringResource(R.string.edit),
                        modifier = modifier.clickable { onFirstActionClick?.invoke() }
                    )
                    Spacer(modifier = modifier.width(16.dp))
                }
                secondImageInRow?.let { imageResId ->
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = stringResource(R.string.edit),
                        modifier = modifier.clickable { onSecondActionClick?.invoke() }
                    )
                    Spacer(modifier = modifier.width(16.dp))
                }
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
    modifier: Modifier = Modifier, @DrawableRes icon: Int, text: String, onClick: () -> Unit
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
        Image(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = modifier.size(20.dp)
        )
        Spacer(modifier = modifier.height(5.5.dp))
        Text(text = text, style = BoldNavTextStyle, color = color)
    }
}

@Composable
fun MenuAppBar(
    modifier: Modifier = Modifier,
    currentScreen: String,
    navController: NavController
) {
    BottomAppBar(
        containerColor = G0,
        contentPadding = PaddingValues(
            top = 10.dp,
            start = 35.5.dp,
            end = 35.5.dp
        ),
        actions = {
            if (currentScreen == "home") NavItem(
                icon = R.drawable.ic_selected_home,
                text = "Home",
                onClick = {},
                color = P300
            )
            else NavItem(icon = R.drawable.ic_home,
                text = "Home",
                onClick = { navController.navigate(HOME_ROUTE) })

            Spacer(modifier = modifier.padding(10.dp))

            if (currentScreen == "transfer") NavItem(
                icon = R.drawable.ic_selected_transfer,
                text = "Transfer",
                onClick = {},
                color = P300
            )
            else NavItem(icon = R.drawable.ic_normal_transfer,
                text = "Transfer",
                onClick = { navController.navigate(AMOUNT_TRANSFER) })

            Spacer(modifier = modifier.padding(10.dp))


            if (currentScreen == "transactions") NavItem(
                icon = R.drawable.ic_selected_history,
                text = "Transactions",
                onClick = {},
                color = P300
            )
            else NavItem(
                icon = R.drawable.ic_normal_history,
                text = "Transactions",
                onClick = { navController.navigate(TRANSACTIONS_HISTORY) })

            Spacer(modifier = modifier.padding(10.dp))

            if (currentScreen == "mycards") NavItem(
                icon = R.drawable.ic_selected_mycard, text = "My Cards", onClick = {}, color = P300
            )
            else NavItem(icon = R.drawable.ic_mycard, text = "My Cards", onClick = {
                navController.navigate(MY_CARDS)
            })

            Spacer(modifier = modifier.padding(10.dp))

            if (currentScreen == "more") NavItem(icon = R.drawable.ic_selected_more,
                text = "More",
                color = P300,
                onClick = {}
            )
            else NavItem(
                icon = R.drawable.ic_more,
                text = "More",
                onClick = { navController.navigate(MORE_ROUTE) }
            )

        },

        modifier = modifier
            .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
            .height(83.dp)
            .fillMaxWidth()

    )
}


@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    title: String,
    text: String,
    onClick: () -> Unit,
    skip: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        GradientStart,
                        GradientEnd
                    )
                )
            )
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = modifier.padding(25.dp))

        Text(
            text = "Skip",
            style = ButtonMedium,
            color = G900,
            modifier = modifier
                .clickable(onClick = skip)
                .align(Alignment.End),
        )
        Spacer(modifier = modifier.padding(15.dp))

        Image(
            painter = painterResource(id = image),
            contentDescription = "$title image",
            modifier = modifier
                .width(350.dp)
                .height(303.dp)
        )
        @DrawableRes val progress: Int
        if (title == "Amount")
            progress = R.drawable.ic_progress_1
        else if (title == "Confirmation")
            progress = R.drawable.ic_progress_2
        else
            progress = R.drawable.ic_progress_3
        Spacer(modifier = modifier.padding(29.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = progress),
                contentDescription = "Progress Image"
            )
            Spacer(modifier = modifier.padding(32.dp))
            Text(text = title, style = HeadingTextStyle, color = G900)
            Spacer(modifier = modifier.padding(16.dp))
            Text(text = text, style = BodyMedium, color = G700)
            Spacer(modifier = modifier.padding(32.dp))
            PrimaryButton(onClick = onClick, buttonText = "Next")


        }

    }
}

@Composable
fun TransactionList(amount: String) {
    Column(
        modifier = Modifier
            .background(OffYellowColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(15.dp))
        Image(
            painter = painterResource(id = R.drawable.group_18305),
            contentDescription = "Account connected successfully",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .width(124.dp)
        )
        Text(
            text = amount,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 18.dp, bottom = 4.dp)
        )
        Text(
            text = "Transfer amount",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = DisabledColor,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "Received money",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = PrimaryColor,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun MycardsItem(modifier: Modifier = Modifier, text1: String, text2: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(P50),
            modifier = Modifier
                .padding(top = 8.dp)
                .height(94.dp)
                .width(360.dp),
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
                    Icon(
                        painter = painterResource(id = R.drawable.bank),
                        contentDescription = "Bank Icon",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = text1,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(
                        text = text2,
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}


@Composable
fun Data(
    labelText: String,
    cardInfo: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = labelText,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = cardInfo,
            onValueChange = onValueChange,
            placeholder = { Text(text = labelText, style = AppTextStyle) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxHeight()
        )
    }
}


@Composable
fun ListItemOfInfo(
    title: String,
    description: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 1.dp),
        colors = CardDefaults.cardColors(OffYellowColor),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .size(180.dp, 50.dp)
        ) {
            Text(
                text = title, fontSize = 16.sp, modifier = Modifier.padding(bottom = 13.dp),
                fontWeight = FontWeight.Bold
            )
            Text(text = description, fontSize = 16.sp, color = Color.Gray)
            BorderStroke(1.dp, color = Color.Gray)
        }

    }
}


@Composable
fun ListItem(
    iconRes: Int,
    title: String,
    description: String,
    onClick: () -> Unit,
    color: Color,
    date: String ="",
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 1.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(color),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = iconRes), contentDescription = title)
            Column(
                modifier = Modifier
                    .padding(start = 24.dp, end = 90.dp)
                    .size(180.dp, 50.dp)
            ) {
                Text(
                    text = title, fontSize = 16.sp, modifier = Modifier.padding(bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(text = description, fontSize = 16.sp, color = Color.Gray)
                Text(text = date, style = CardDetailsTextStyle, color = G100)
            }
            Image(
                painter = painterResource(id = R.drawable.path),
                contentDescription = "to",
                modifier = Modifier.size(13.dp)
            )
        }

    }
}

@Composable
fun cardimp(text1: String, text2: String) {
    Card(
        colors = CardDefaults.cardColors(P50),
        modifier = Modifier
            .height(126.dp)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 12.dp),
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
                Text(
                    text = "${text1}\n${text2}",
                    style = BodyMediumBold,
                    color = PrimaryColor,
                )
                Spacer(modifier = Modifier.padding(6.dp))
            }
        }
    }
}


@Composable
fun BoxList(
    icon: Int,
    title: String,
    description: String,
    OnClick: () -> Unit,
    status: String,
    date: String,
) {
    Box(modifier = Modifier.padding(16.dp)) {
        ListItem(
            iconRes = icon,
            title = title,
            description = description,
            onClick = OnClick,
            color = Color.White,
            date = date,
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.path),
                contentDescription = "to",
                modifier = Modifier
                    .padding(5.dp)
                    .size(15.dp)
            )
            if(status == "successful") {
                Text(
                    text = "Successful",
                    style = SmallTextStyle,
                    color = Color(0xFF118A30)
                )
            }else{
                Text(
                    text = "Failed",
                    style = SmallTextStyle,
                    color = AlertColor
                )

            }
        }
    }
}