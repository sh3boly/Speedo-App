package com.example.speedoapp.ui.more

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedoapp.R
import com.example.speedoapp.model.LoginStatus
import com.example.speedoapp.model.LogoutStatus
import com.example.speedoapp.ui.help.HelpItem
import com.example.speedoapp.navigation.AppRoutes.FAVOURITE_ROUTE
import com.example.speedoapp.navigation.AppRoutes.HOME_ROUTE
import com.example.speedoapp.navigation.AppRoutes.PROFILE
import com.example.speedoapp.navigation.AppRoutes.SIGNIN_ROUTE
import com.example.speedoapp.ui.common.MenuAppBar
import com.example.speedoapp.ui.common.TopBar
import com.example.speedoapp.ui.homepage.HomeViewModel
import com.example.speedoapp.ui.theme.BodyMediumBold
import com.example.speedoapp.ui.theme.G0
import com.example.speedoapp.ui.theme.G200
import com.example.speedoapp.ui.theme.G40
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.RedYellowColor
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val logoutStatus by viewModel.logoutStatus.collectAsState()


    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
        sheetPeekHeight = 0.dp,
        sheetContainerColor = G0,
        scaffoldState = scaffoldState,
        sheetContent = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 55.43.dp)
                    .padding(bottom = 83.dp)
            ) {
                HelpItem(
                    text = stringResource(R.string.send_email),
                    contentDescription = stringResource(R.string.send_email),
                    image = R.drawable.ic_email,
                    identifier = 1,
                )
                Spacer(modifier = modifier.width(32.dp))
                HelpItem(
                    text = stringResource(R.string.call_us),
                    contentDescription = stringResource(R.string.call_us),
                    isCall = true,
                    phoneNumber = "19888",
                    image = R.drawable.ic_call,
                    identifier = 0
                )
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
                        title = stringResource(R.string.more),
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
                    modifier = modifier
                        .imePadding()
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                ) {
                    MoreItem(
                        text = stringResource(R.string.transfer_from_website),
                        image = R.drawable.ic_website,
                        onClick = {
                            val url = "https://speedo-transfer.vercel.app/main-page/transfer"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        },
                        contentDescription = stringResource(R.string.transfer_from_website)
                    )

                    MoreItem(
                        text = stringResource(R.string.favourites),
                        image = R.drawable.ic_favorite,
                        onClick = {
                            Log.d("YOO", "Iam here")
                            navController.navigate(FAVOURITE_ROUTE)
                            Log.d("YOO", "Iam here")

                        },
                        contentDescription = stringResource(R.string.favourites)
                    )

                    MoreItem(
                        text = stringResource(R.string.profile),
                        image = R.drawable.ic_user,
                        onClick = {navController.navigate(PROFILE) },
                        contentDescription = stringResource(R.string.profile)
                    )

                    MoreItem(
                        text = stringResource(R.string.help),
                        image = R.drawable.ic_alert,
                        onClick = {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand() // Open the sheet
                            }
                        },
                        contentDescription = stringResource(R.string.help)
                    )

                    MoreItem(
                        text = stringResource(R.string.logout),
                        image = R.drawable.ic_logout,
                        onClick = {
                            viewModel.logout()
                        },
                        contentDescription = stringResource(R.string.logout),
                        divider = false
                    )
                    LaunchedEffect(logoutStatus) {
                        logoutStatus?.let { status ->
                            when (status) {
                                is LogoutStatus.Success -> {
                                    Log.d("API", "eh el 3abat da")
                                    Toast.makeText(
                                        context,
                                        "Log out Successful!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    Log.d("API", "ba3d el 3abat")

                                    navController.navigate(SIGNIN_ROUTE)
                                    Log.d("API", "eh da")


                                }
                                is LogoutStatus.Error -> {
                                    Toast.makeText(
                                        context,
                                        "Log out failed! Error: ${status.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MoreItem(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes image: Int,
    onClick: () -> Unit,
    contentDescription: String,
    divider: Boolean = true
) {
    Column(modifier = modifier.clickable { onClick() }) {
        Spacer(modifier = modifier.height(16.dp))
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Row(horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = contentDescription,
                    modifier = modifier.size(24.dp)
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(text = text, style = BodyMediumBold, color = G200)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                tint = G200,
                contentDescription = stringResource(
                    R.string.arrow_icon
                ),
                modifier = modifier.size(24.dp)
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        if (divider)
            HorizontalDivider(color = G40)
    }
}