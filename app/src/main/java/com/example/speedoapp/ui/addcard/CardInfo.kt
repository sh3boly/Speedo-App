package com.example.speedoapp.ui.addcard

data class CardInfo(val userID:String,
    var cardHolder:String, val cardNo: String, val expiryDate: String,
    val CVV:String, var isLoading: Boolean = false,
    val error:String? = null,)