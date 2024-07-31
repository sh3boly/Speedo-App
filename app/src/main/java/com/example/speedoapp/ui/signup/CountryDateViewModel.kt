package com.example.speedoapp.ui.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

class CountryDateViewModel : ViewModel() {
    private val _countries = MutableStateFlow<List<String>>(emptyList())
    val countries: StateFlow<List<String>> = _countries


    init {
        getCountries()
    }
    private fun getCountries() {
        val isoCountryCodes: Array<String> = Locale.getISOCountries()
        val countriesWithEmojis: ArrayList<String> = arrayListOf()
        for (countryCode in isoCountryCodes) {
            val locale = Locale("", countryCode)
            val countryName: String = locale.displayCountry
            val flagOffset = 0x1F1E6
            val asciiOffset = 0x41
            val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
            val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
            val flag =
                (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))
            countriesWithEmojis.add("$flag  $countryName ")
        }
        _countries.value = countriesWithEmojis
    }
}