package com.example.speedoapp.ui.addcard

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.speedoapp.model.CardInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddCardViewModel: ViewModel() {
    val _cardInfo = MutableStateFlow(CardInfo("", "", "", ""))
    val cardInfo: StateFlow<CardInfo> = _cardInfo.asStateFlow()
    private val _addedCards = MutableStateFlow<List<CardInfo>>(emptyList())
    val addedCards: StateFlow<List<CardInfo>> = _addedCards.asStateFlow()



    @RequiresApi(Build.VERSION_CODES.O)

    fun updateCardholderName(name: String) {
        _cardInfo.value = _cardInfo.value.copy(cardHolder = name)
        Log.d("AddCardViewModel", "cardHolder updated: $name")
    }

    fun updateCardNumber(number: String) {
        _cardInfo.value = _cardInfo.value.copy(cardNo = number)
    }

    fun updateExpiryDate(date: String) {
        _cardInfo.value = _cardInfo.value.copy(expiryDate = date)
    }

    fun updateCVV(cvv: String) {
        _cardInfo.value = _cardInfo.value.copy(cvv = cvv)
    }

    //validation
    fun isValidCardHolder(name: String): Boolean {
        return if (name.isEmpty()) false else true
    }

    fun isValidCardNumber(number: String): Boolean {
        return if (number.length == 16 && number.all { it.isDigit() }) true else false
    }

    fun isValidCVV(cvv: String): Boolean {
        return if (cvv.length == 3 && cvv.all { it.isDigit() }) true else false
    }

    @RequiresApi(Build.VERSION_CODES.O)
//    fun isValidExpiryDate(date: String): Boolean {
//        if (date.length != 4) return false
//
//        val monthStr = date.substring(0, 2)
//        val yearStr = date.substring(3)
//
//        val month = monthStr.toIntOrNull() ?: return false
//        if (month !in 1..12) return false
//
//        val currentYear = LocalDate.now().year
//        val fullYear = (currentYear / 100) * 100 + yearStr.toInt()
//
//        val formatter = DateTimeFormatter.ofPattern("MM/yyyy")
//        val expiryDate = LocalDate.parse("$month/$fullYear", formatter)
//        val currentDate = LocalDate.now()
//
//        return expiryDate.year > currentDate.year ||
//                (expiryDate.year == currentDate.year && expiryDate.month >= currentDate.month)
//    }
    fun isValidExpiryDate(date: String): Boolean {
        if (!Regex("^\\d{2}[\\\\/]\\d{2}$").matches(date)) return false

        val monthStr = date.substring(0, 2)
        val yearStr = date.substring(2)

        val month = monthStr.toIntOrNull() ?: return false
        if (month !in 1..12) return false

        val currentYear = LocalDate.now().year
        val fullYear = (currentYear / 100) * 100 + yearStr.toInt()

        val formatter = DateTimeFormatter.ofPattern("MM/yyyy")
        val expiryDate = LocalDate.parse("$month/$fullYear", formatter)
        val currentDate = LocalDate.now()

        return expiryDate.year > currentDate.year || (expiryDate.year == currentDate.year && expiryDate.monthValue >= currentDate.monthValue)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun submitCard(card: CardInfo): Boolean {
        if (isValidCardNumber(card.cardNo) && isValidCardHolder(card.cardHolder)
            && isValidCVV(card.cvv)
        ) {
            _addedCards.update { it + card }
            return true
        } else {
            return false
        }
        return false
    }
}
