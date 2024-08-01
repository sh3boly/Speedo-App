package com.example.speedoapp.ui.common

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedoapp.R
import com.example.speedoapp.ui.theme.AlertColor
import com.example.speedoapp.ui.theme.AppNumbersStyle
import com.example.speedoapp.ui.theme.AppTextStyle
import com.example.speedoapp.ui.theme.AppTextStyleSelected
import com.example.speedoapp.ui.theme.BodyMedium
import com.example.speedoapp.ui.theme.ButtonTextColor
import com.example.speedoapp.ui.theme.DisabledColor
import com.example.speedoapp.ui.theme.G0
import com.example.speedoapp.ui.theme.G100
import com.example.speedoapp.ui.theme.G70
import com.example.speedoapp.ui.theme.G700
import com.example.speedoapp.ui.theme.G900
import com.example.speedoapp.ui.theme.PrimaryColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    buttonText: String
) {
    Button(
        onClick = onClick, colors = ButtonColors(
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
        Text(text = buttonText, style = AppTextStyle)
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
            containerColor = Color.White,
            contentColor = PrimaryColor,
            disabledContainerColor = DisabledColor,
            disabledContentColor = ButtonTextColor
        ),
        enabled = isEnabled,
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
            .width(343.dp)
            .height(51.dp)
    ) {
        Text(text = buttonText, style = AppTextStyle)


    }
}

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
            isError = isError != null,
            visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = label, style = AppTextStyle)
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
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                title, style = SubTitleTextStyle
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = color // Set the background color here
        ),
        navigationIcon = {
            if (navigationIcon) {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
            }
        },

        )
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
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            enabled = false,
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                if (label == "Country")
                    Text(text = "Select your $label", style = AppTextStyle)
                else
                    Text(text = "DD/MM/YYYY", style = AppTextStyle)
            },
            trailingIcon = {
                Image(painterResource(id = image), imageDescription, Modifier.size(24.dp))


            },
            modifier = Modifier
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

@Preview(showBackground = true)
@Composable
fun ComposablePreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Stepper(currentStep = 3)
    }
}
