package com.example.speedoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.speedoapp.R
import com.example.speedoapp.api.InactivityManager
import com.example.speedoapp.constants.Constants.EMAIL
import com.example.speedoapp.constants.Constants.IDENTIFIER
import com.example.speedoapp.constants.Constants.NAME
import com.example.speedoapp.constants.Constants.PASSWORD
import com.example.speedoapp.navigation.AppRoutes.AMOUNT_ONBOARDING_ROUTE
import com.example.speedoapp.navigation.AppRoutes.AMOUNT_TRANSFER
import com.example.speedoapp.navigation.AppRoutes.CONFIRMATION_ONBOARDING_ROUTE
import com.example.speedoapp.navigation.AppRoutes.CONFIRM_TRANSFER
import com.example.speedoapp.navigation.AppRoutes.SIGNUP_ROUTE
import com.example.speedoapp.navigation.AppRoutes.COUNTRYDATE_ROUTE
import com.example.speedoapp.navigation.AppRoutes.HOME_ROUTE
import com.example.speedoapp.navigation.AppRoutes.PAYMENT_ONBOARDING_ROUTE
import com.example.speedoapp.navigation.AppRoutes.PAYMENT_TRANSFER
import com.example.speedoapp.navigation.AppRoutes.SELECT_CURRENCY
import com.example.speedoapp.navigation.AppRoutes.SIGNIN_ROUTE
import com.example.speedoapp.ui.homepage.HomeScreen
import com.example.speedoapp.ui.signin.SignInScreen

import com.example.speedoapp.ui.common.CurrenciesScreen
import com.example.speedoapp.ui.common.OnboardingScreen
import com.example.speedoapp.ui.signup.CountryDateScreen
import com.example.speedoapp.ui.signup.SignUpScreen
import com.example.speedoapp.ui.tranfer.AmountScreen
import com.example.speedoapp.ui.tranfer.AmountScreenViewModel
import com.example.speedoapp.ui.tranfer.ConfirmScreen
import com.example.speedoapp.ui.tranfer.PaymentScreen


object AppRoutes {
    const val SIGNUP_ROUTE = "signup"
    const val COUNTRYDATE_ROUTE = "countrydate"
    const val SIGNIN_ROUTE = "signin"
    const val AMOUNT_TRANSFER = "amount_transfer"
    const val SELECT_CURRENCY = "select_currency"
    const val CONFIRM_TRANSFER = "confirm_transfer"
    const val PAYMENT_TRANSFER = "payment_Transfer"
    const val HOME_ROUTE = "home"
    const val AMOUNT_ONBOARDING_ROUTE = "amount"
    const val CONFIRMATION_ONBOARDING_ROUTE = "confirmation"
    const val PAYMENT_ONBOARDING_ROUTE = "payment"
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier, firstTime: Boolean) {
    val navController = rememberNavController()
    val amountScreenViewModel: AmountScreenViewModel = viewModel()
    InactivityManager.init(navController)
    NavHost(
        navController = navController,
        startDestination = if (firstTime) AMOUNT_ONBOARDING_ROUTE else SIGNIN_ROUTE,
        modifier = modifier
    ) {
        composable(route = SIGNUP_ROUTE) { SignUpScreen(navController) }
        composable(route = SIGNIN_ROUTE) { SignInScreen(navController) }

        composable(
            route = "$COUNTRYDATE_ROUTE/{$NAME}/{$EMAIL}/{$PASSWORD}",
            arguments = listOf(
                navArgument(NAME) { type = NavType.StringType },
                navArgument(EMAIL) { type = NavType.StringType },
                navArgument(PASSWORD) { type = NavType.StringType }
            )
        ) {
            val name = it.arguments?.getString(NAME)!!
            val email = it.arguments?.getString(EMAIL)!!
            val password = it.arguments?.getString(PASSWORD)!!


            CountryDateScreen(navController, name, email, password)
        }
        composable(route = HOME_ROUTE) { HomeScreen(navController = navController) }

        composable(AMOUNT_TRANSFER) {
            AmountScreen(
                navController,
                viewModel = amountScreenViewModel
            )
        }
        composable(
            route = "$SELECT_CURRENCY/{$IDENTIFIER}",
            arguments = listOf(navArgument(IDENTIFIER) {
                type = NavType.IntType
            })
        )
        {
            val identifier = it.arguments?.getInt(IDENTIFIER)

            CurrenciesScreen(
                navController = navController,
                identifier = identifier!!,
                viewModel = amountScreenViewModel,
                onCurrencySelected = { selectedCurrency ->
                    if (identifier == 0) {
                        amountScreenViewModel.updateCurrencyFrom(selectedCurrency)
                    } else {
                        amountScreenViewModel.updateCurrencyTo(selectedCurrency)
                    }
                }
            )

        }

        composable(route = CONFIRM_TRANSFER) {
            ConfirmScreen(navController = navController, viewModel = amountScreenViewModel)
        }
        composable(route = PAYMENT_TRANSFER) {
            PaymentScreen(navController = navController, viewModel = amountScreenViewModel)
        }
        composable(route = AMOUNT_ONBOARDING_ROUTE) {
            OnboardingScreen(
                image = R.drawable.ic_amount,
                title = "Amount",
                text = "Send money fast with simple steps. Create account, Confirmation, Payment. Simple",
                onClick = { navController.navigate(CONFIRMATION_ONBOARDING_ROUTE) },
                skip = { navController.navigate(SIGNUP_ROUTE) }
            )
        }
        composable(route = CONFIRMATION_ONBOARDING_ROUTE) {
            OnboardingScreen(
                image = R.drawable.ic_confirmation,
                title = "Confirmation",
                text = "Transfer funds instantly to friends and family worldwide, strong shield protecting a money.",
                onClick = { navController.navigate(PAYMENT_ONBOARDING_ROUTE) },
                skip = { navController.navigate(SIGNUP_ROUTE) }
            )
        }
        composable(route = PAYMENT_ONBOARDING_ROUTE) {
            OnboardingScreen(
                image = R.drawable.ic_payment,
                title = "Payment",
                text = "Enjoy peace of mind with our secure platform  Transfer funds instantly to friends.",
                onClick = { navController.navigate(SIGNUP_ROUTE) },
                skip = { navController.navigate(SIGNUP_ROUTE) }
            )
        }
    }
}

