package com.example.speedoapp.ui.addcard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddCardViewModel: ViewModel() {
    val _cardInfo = MutableStateFlow(CardInfo("", "", "", "", false, error = null))
    val cardInfo: StateFlow<CardInfo> = _cardInfo.asStateFlow()

    fun updateCardholderName(name: String) {
        _cardInfo.value = _cardInfo.value.copy(cardHolder = name)
    }

    fun updateCardNumber(number: String) {
        _cardInfo.value = _cardInfo.value.copy(cardNo = number)
    }

    fun updateExpiryDate(date: String) {
        _cardInfo.value = _cardInfo.value.copy(expiryDate = date)
    }

    fun updateCVV(cvv: String) {
        _cardInfo.value = _cardInfo.value.copy(CVV = cvv)
    }

    //validation
    fun isValidCardHolder(name: String):String?{
        return if (name.isEmpty()) "required" else null
    }
    fun isValidCardNumber(number: String): String? {
        return if (number.length == 16 && number.all { it.isDigit() }) null else "length=16 and enter digits"
    }

    fun isValidCVV(cvv: String): String? {
        return if (cvv.length == 3 && cvv.all { it.isDigit() }) null else "length=3 and enter digits"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isValidExpiryDate(date: String): String? {
        val formatter = DateTimeFormatter.ofPattern("MM/yy")
        try {
            val expiryDate = LocalDate.parse(date, formatter)
            val currentDate = LocalDate.now()
            return if (expiryDate.year > currentDate.year ||
                (expiryDate.year == currentDate.year && expiryDate.monthValue >= currentDate.monthValue))
                null else "enter valid date"
        } catch (e: Exception) {
            return "enter valid date"
        }
    }
    fun submitCard(){
        viewModelScope.launch { _cardInfo.value=_cardInfo.value.copy(isLoading = true) }
        //call API
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun validateCardData(): AddCardError? {
//        val cardInfo = _cardInfo.value
//
//        return when {
//            cardInfo.cardHolder.isEmpty() || cardInfo.cardNo.isEmpty() || cardInfo.expiryDate.isEmpty() || cardInfo.CVV.isEmpty() -> AddCardError.EmptyField
//            !isValidCardNumber(cardInfo.cardNo) -> AddCardError.InvalidCardNumber
//            !isValidExpiryDate(cardInfo.expiryDate) -> AddCardError.InvalidExpirationDate
//            !isValidCVV(cardInfo.CVV) -> AddCardError.InvalidCVV
//            else -> null
//        }
//    }
}