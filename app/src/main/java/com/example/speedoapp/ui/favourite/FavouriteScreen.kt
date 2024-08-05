package com.example.speedoapp.ui.favourite

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedoapp.R
import com.example.speedoapp.navigation.AppRoutes.HOME_ROUTE
import com.example.speedoapp.ui.common.AccountCard
import com.example.speedoapp.ui.common.MenuAppBar
import com.example.speedoapp.ui.common.TopBar
import com.example.speedoapp.ui.theme.G0
import com.example.speedoapp.ui.theme.G900
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.RedYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyleBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: FavouriteViewModel = viewModel()
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
        sheetPeekHeight = 0.dp,
        sheetContainerColor = G0,
        scaffoldState = scaffoldState,
        sheetContent = {
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
                    AccountCard(
                        identifier = 1,
                        cardHolder = "Asmaa Dosuky",
                        cardNumber = "4564",
                        firstImageInRow = R.drawable.ic_edit,
                        secondImageInRow = R.drawable.ic_delete,
                        onFirstActionClick = {
                        },
                        onSecondActionClick = {

                        }
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    AccountCard(
                        identifier = 1,
                        cardHolder = "Asmaa Dosuky",
                        cardNumber = "4564",
                        firstImageInRow = R.drawable.ic_edit,
                        secondImageInRow = R.drawable.ic_delete
                    )
                }
            }
        }
    }
}
