package com.example.speedoapp.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

val AppTextStyle = TextStyle(
    fontSize = 14.sp,
    lineHeight = 21.sp,
    fontWeight = FontWeight(400),
)

val TitleTextStyle = TextStyle(
    fontSize = 24.sp,
    lineHeight = 29.05.sp,
    textAlign = TextAlign.Center,
    fontWeight = FontWeight(600),
)

val SubTitleTextStyle = TextStyle(
    fontSize = 20.sp,
    lineHeight = 30.sp,
    textAlign = TextAlign.Center,
    fontWeight = FontWeight(500),
)

val ClickAbleTextStyle = TextStyle(
    fontSize = 14.sp,
    lineHeight = 20.8.sp,
    fontWeight = FontWeight(500),
    color = PrimaryColor,
    textDecoration = TextDecoration.Underline,

)