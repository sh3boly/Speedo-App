package com.example.speedoapp.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedoapp.ui.common.PasswordField
import com.example.speedoapp.ui.common.PrimaryButton
import com.example.speedoapp.ui.theme.OffYellowColor
import com.example.speedoapp.ui.theme.SubTitleTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePass(modifier: Modifier = Modifier) {
    var password by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Change Password", style = SubTitleTextStyle)
                }, colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = OffYellowColor
                )
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize()
            .background(OffYellowColor)){
            PasswordField(value = "", onValueChange ={} , label ="enter current password")
            Spacer(modifier = Modifier.padding(5.dp))
            PasswordField(value = "", onValueChange = {}, label = "enter new password")
            Spacer(modifier = Modifier.padding(15.dp))
            PrimaryButton(onClick = { /*TODO*/ }, buttonText = "Save")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ChangePassPreview(){
    ChangePass()
}