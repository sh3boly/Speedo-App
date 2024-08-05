package com.example.speedoapp.ui.tranfer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.speedoapp.R
import com.example.speedoapp.model.Currency
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.common.TopBar
import com.example.speedoapp.ui.theme.BodyMediumBold
import com.example.speedoapp.ui.theme.G0
import com.example.speedoapp.ui.theme.G100
import com.example.speedoapp.ui.theme.G40
import com.example.speedoapp.ui.theme.P50
import com.example.speedoapp.ui.theme.PrimaryColor
import com.example.speedoapp.ui.theme.RedYellowColor

@Composable
fun CurrenciesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    identifier: Int,
    onCurrencySelected: (Currency) -> Unit,
    viewModel: AmountScreenViewModel = viewModel()
) {
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
                LazyColumn(modifier = modifier.height(600.dp)) {
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
                                    text = currency.name, style = BodyMediumBold, color = G100
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