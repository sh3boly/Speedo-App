package com.example.speedoapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.speedoapp.constants.Constants.EMAIL
import com.example.speedoapp.constants.Constants.NAME
import com.example.speedoapp.constants.Constants.PASSWORD
import com.example.speedoapp.navigation.AppRoutes.SIGNUP_ROUTE
import com.example.speedoapp.navigation.AppRoutes.COUNTRYDATE_ROUTE

import com.example.speedoapp.ui.signup.CountryDateScreen
import com.example.speedoapp.ui.signup.SignUpScreen

object AppRoutes {
    const val SIGNUP_ROUTE = "signup"
    const val COUNTRYDATE_ROUTE = "countrydate"
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SIGNUP_ROUTE, modifier = modifier) {
        composable(route = SIGNUP_ROUTE) { SignUpScreen(navController) }
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


            CountryDateScreen(name, email, password)
        }
    }

}