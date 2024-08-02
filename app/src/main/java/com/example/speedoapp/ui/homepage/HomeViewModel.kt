package com.example.speedoapp.ui.homepage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedoapp.api.RetrofitFactory
import com.example.speedoapp.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _balance = MutableStateFlow<Float>(0f)
    val balance = _balance.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions = _transactions.asStateFlow()

    private val _hasError = MutableStateFlow(0)
    val hasError = _hasError.asStateFlow()

    init {
        getName()
    }

    fun getBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitFactory.homeApi.getBalance()
                if (response.isSuccessful) {
                    val balanceResponse = response.body()
                    if (balanceResponse != null)
                        _balance.value = balanceResponse


                } else {
                    Log.d("trace", "Error: ${response.message()}")
                    _hasError.value += 1

                }
            } catch (e: Exception) {
                Log.d("trace", "Exception: ${e.localizedMessage}")
                _hasError.value += 1

            }
        }
    }

    fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _transactions.update {
                    RetrofitFactory.homeApi.getTransactions().transactions

                }
            } catch (e: Exception) {
                Log.d("trace", "Exception: ${e.localizedMessage}")
                _hasError.value += 1
            }
        }
    }

    fun getName() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val body = RetrofitFactory.homeApi.getUser()
                _name.value = body.name

            } catch (e: Exception) {
                Log.d("trace", "Exception: ${e.localizedMessage}")
                _hasError.value += 1

            }
        }
    }

    fun getInitials(fullName: String): String {
        if (fullName.isNullOrEmpty()) {
            return ""
        }
        if (fullName.contains(" ")) {
            val names = fullName.split(" ")
            return names.mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString("")
        }
        return fullName.first().toString()
    }
}