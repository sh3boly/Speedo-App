package com.example.speedoapp.ui.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedoapp.R
import com.example.speedoapp.api.UpdateUserService
import com.example.speedoapp.model.UpdateUserRequest
import com.example.speedoapp.ui.common.DataField
import com.example.speedoapp.ui.common.DataFieldInt
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.signup.CountryPicker
import com.example.speedoapp.ui.signup.DatePicker
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(navController: NavController, modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableIntStateOf(+20) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Edit Profile", style = SubTitleTextStyle)
                }, colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = OffYellowColor
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .padding(20.dp)
                    .clickable { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .background(OffYellowColor)
        ) {
            DataField(
                isError = "",
                value = "",
                onValueChange = { name = it },
                label = "Full Name",
                image = null,
                typingImage = R.drawable.ic_person_typing,
                type = KeyboardType.Text,
            )
            DataFieldInt(
                isError = "",
                value = phone,
                onValueChange = { phone = it },
                label = "Phone number",
                image = null,
                typingImage = R.drawable.ic_person_typing,
                type = KeyboardType.Number,
            )
        }

        CountryPicker()
        Spacer(modifier = Modifier.padding(10.dp))
        DatePicker()
        Spacer(modifier = Modifier.padding(15.dp))
        PrimaryButton(onClick = {}, buttonText = "Save")
    }
    }

@Preview(showBackground = true)
@Composable
fun EditPreview(){
    val nav= rememberNavController()
    EditProfile(navController = nav)
}