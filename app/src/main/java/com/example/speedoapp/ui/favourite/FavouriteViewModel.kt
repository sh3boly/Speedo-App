package com.example.speedoapp.ui.favourite

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedoapp.api.RetrofitFactory
import com.example.speedoapp.model.Favourite
import com.example.speedoapp.model.FavouriteRequest
import com.example.speedoapp.utils.readJsonFromAssets
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouriteViewModel : ViewModel() {
    private val _favourites = MutableStateFlow<List<Favourite>>(emptyList())
    val favourites = _favourites.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    private val _toBeEdited = MutableStateFlow<Favourite?>(null)

    init {
        getFavourites()
    }

    private fun getFavourites() {
        viewModelScope.launch {
            try {
                val response = RetrofitFactory.favouriteApi.getFavourites()
                if (response.isSuccessful) {
                    _favourites.value = response.body() ?: emptyList()
                } else {
                    _hasError.value = true
                }
            } catch (e: Exception) {
                _hasError.value = false
            }
        }
    }

    fun deleteFavourite(id: String) {
        viewModelScope.launch {
            //_favourites.value = _favourites.value.filterNot { it.id.toString() == id }
            try {
                val response = RetrofitFactory.favouriteApi.deleteFavourite(id)
                if (response.isSuccessful) {
                    getFavourites()
                } else {
                    _hasError.value = true
                }
            } catch (e: Exception) {
                _hasError.value = false
            }
        }
    }

    fun updateFavourite(name: String, number: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitFactory.favouriteApi.updateFavourite(
                    _toBeEdited.value?.id.toString(), FavouriteRequest(name, number)
                )
                if (response.isSuccessful) {
                    getFavourites()
                } else {
                    _hasError.value = true
                }
            } catch (e: Exception) {
                _hasError.value = false
            }
        }
    }

    fun loadFavouritesFromLocal(context: Context) {
        viewModelScope.launch {
            val jsonString = readJsonFromAssets(context, "favourites.json")
            val favouriteType = object : TypeToken<List<Favourite>>() {}.type

            val favouriteList = Gson().fromJson<List<Favourite>>(jsonString, favouriteType)
            if (favouriteList != null) {
                _favourites.update { favouriteList }
                _hasError.value = false
            } else {
                Log.d("HERE", "ERROR")

                _hasError.value = true
            }
        }
    }

    fun updateFavouritesForLocal(name: String, number: String) {

        val favouriteToUpdate = _toBeEdited.value

        if (favouriteToUpdate == null) {
            _hasError.value = true
            return
        }

        _favourites.value = _favourites.value.map {
            if (it.id == favouriteToUpdate.id) {
                // Update the favourite in the local list
                it.copy(
                    recipientName = name, recipientAccountNumber = number
                )
            } else {
                it
            }
        }
        _toBeEdited.value = null
    }

    fun validateEditRequest(name: String, number: String): Boolean {
        return ((name != _toBeEdited.value?.recipientName || number != _toBeEdited.value?.recipientAccountNumber) && name.isNotEmpty() && number.isNotEmpty())
    }

    fun updateToBeEdited(favourite: Favourite) {
        _toBeEdited.value = favourite
    }

    fun checkAccountNumber(number: String): String? {
        return if (number.length != 16) "Please enter valid account number"
        else null
    }
}
