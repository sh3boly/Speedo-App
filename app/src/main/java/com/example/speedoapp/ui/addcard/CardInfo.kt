package com.example.speedoapp.ui.addcard

data class CardInfo(
    var cardHolder:String, val cardNo: String, val expiryDate: String,
    val CVV:String, val isLoading: Boolean = false,
    val error:String? = null,)