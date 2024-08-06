package com.example.speedoapp.ui.favourite

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedoapp.R
import com.example.speedoapp.navigation.AppRoutes.HOME_ROUTE
import com.example.speedoapp.ui.common.AccountCard
import com.example.speedoapp.ui.common.DataField
import com.example.speedoapp.ui.common.MenuAppBar
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.common.TopBar
import com.example.speedoapp.ui.theme.BodyMediumBold
import com.example.speedoapp.ui.theme.G0
import com.example.speedoapp.ui.theme.G700
import com.example.speedoapp.ui.theme.G900
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.P300
import com.example.speedoapp.ui.theme.RedYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyleBold
import com.example.speedoapp.ui.theme.SubTitleTextStyleLight
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: FavouriteViewModel = viewModel()
) {
    var enteredName by remember { mutableStateOf("") }
    var enteredNumber by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    //viewModel.loadFavouritesFromLocal(context)

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    val favourites by viewModel.favourites.collectAsState()

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
        sheetPeekHeight = 0.dp,
        sheetContainerColor = G0,
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = stringResource(R.string.edit),
                        colorFilter = ColorFilter.tint(P300)
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.edit),
                        style = SubTitleTextStyleLight,
                        color = G700
                    )
                }

                Spacer(modifier = modifier.height(16.dp))
                DataField(value = enteredName, onValueChange = {
                    enteredName = it
                }, label = "Recipient Account", type = KeyboardType.Text)
                Spacer(modifier = modifier.height(8.dp))
                DataField(
                    value = enteredNumber,
                    onValueChange = {
                        enteredNumber = it
                        errorMessage = null
                    },
                    label = "Recipient Account Number",
                    type = KeyboardType.Number,
                    isError = errorMessage
                )
                Spacer(modifier = modifier.height(32.dp))
                PrimaryButton(
                    onClick = {
                        errorMessage = viewModel.checkAccountNumber(enteredNumber)
                        if (errorMessage == null) {
                            //viewModel.updateFavouritesForLocal(enteredName, enteredNumber)
                            viewModel.updateFavourite(enteredName, enteredNumber)
                            scope.launch {
                                scaffoldState.bottomSheetState.partialExpand()
                            }
                        }
                    },
                    buttonText = stringResource(R.string.save),
                    isEnabled = viewModel.validateEditRequest(enteredName, enteredNumber)
                )
                Spacer(modifier = modifier.height(135.dp))
            }

        }
    ) {

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
                bottomBar = {
                    MenuAppBar(currentScreen = "more", navController = navController)
                },
                topBar = {
                    TopBar(
                        title = stringResource(id = R.string.favourite),
                        navigationIcon = true,
                        color = Color.Transparent,
                        onNavigationIconClick = {
                            navController.navigate(HOME_ROUTE)
                        },
                        actions = false,
                        onActionsClick = {},
                        actionsText = ""
                    )
                },
            ) { innerPadding ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .imePadding()
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = modifier.height(32.11.dp))
                    Text(
                        text = stringResource(R.string.your_favourite_list),
                        style = SubTitleTextStyleBold,
                        color = G900
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    LazyColumn {
                        items(favourites) { favourite ->
                            AccountCard(
                                identifier = favourite.id,
                                cardHolder = favourite.recipientName,
                                cardHolderTextStyle = BodyMediumBold,
                                cardNumber = favourite.recipientAccountNumber,
                                firstImageInRow = R.drawable.ic_edit,
                                secondImageInRow = R.drawable.ic_delete,
                                onFirstActionClick = {
                                    scope.launch {
                                        errorMessage = null
                                        enteredName = favourite.recipientName
                                        enteredNumber = favourite.recipientAccountNumber
                                        viewModel.updateToBeEdited(favourite)
                                        scaffoldState.bottomSheetState.expand()

                                    }
                                },
                                onSecondActionClick = {
                                    viewModel.deleteFavourite(favourite.id.toString())
                                    Toast.makeText(context, "Favourite deleted", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            )
                            Spacer(modifier = modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}
