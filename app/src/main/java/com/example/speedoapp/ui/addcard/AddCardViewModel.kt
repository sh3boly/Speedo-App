package com.example.speedoapp.ui.addcard

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.Result

class AddCardViewModel: ViewModel() {
    val _cardInfo = MutableStateFlow(CardInfo("", "", "", "", "", false, error = null))
    val cardInfo: StateFlow<CardInfo> = _cardInfo.asStateFlow()

    //private val _addedCards = mutableStateListOf<CardInfo>()
    //val addedCards: List<CardInfo> = _addedCards

    private val _addedCards = MutableStateFlow<List<CardInfo>>(emptyList())
    val addedCards: StateFlow<List<CardInfo>> = _addedCards.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    //val addedCards: List<CardInfo> = _addedCards.toList() // Read-only view

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
            && isValidCVV(card.CVV)
        ) {
            card.isLoading = true
            //addedCards.add(card)
            _addedCards.update { it + card }
            //Log.d("AddCardViewModel", "Card added: ${card.cardHolder}")
            Log.d("AddCardViewModel", "Added cards: ${_addedCards.value},List size: ${_addedCards.value.size}")
            return true
        } else {
            card.isLoading = false
            Log.d("AddCardViewModel", "Card validation failed")
        }
        //_addedCards.add(card.copy())
        return false
    }

//        fun getAllCards(userID: String): List<CardInfo>{
//        return _addedCards.filter { it.userID == userID }
//    }
    fun getAllCards(userID: String): Flow<List<CardInfo>> {
        return _addedCards.map { cards ->
            cards.filter { it.userID == userID }
        }
    }
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