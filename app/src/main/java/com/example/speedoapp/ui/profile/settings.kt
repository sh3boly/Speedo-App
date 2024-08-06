package com.example.speedoapp.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.speedoapp.R
import com.example.speedoapp.navigation.AppRoutes
import com.example.speedoapp.ui.common.ListItem
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Settings", style = SubTitleTextStyle)
            },colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = OffYellowColor
            ))
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .padding(20.dp)
                    .clickable { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.background(OffYellowColor)
            .fillMaxSize()
            .padding(innerPadding).padding(top=28.dp)) {
            ListItem(iconRes = R.drawable.group_183255, title = "Change Password", description ="Change Password" , onClick = {navController.navigate(AppRoutes.CHANGEPASS)},
                OffYellowColor)
            ListItem(iconRes = R.drawable.group_183256, title = "Edit Profile", description ="Change your Information",
                onClick = {navController.navigate(AppRoutes.EDIT_PROFILE)}, OffYellowColor )
        }

}
}