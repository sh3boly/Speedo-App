package com.example.speedoapp.ui.homepage

import PreferencesManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.speedoapp.api.RetrofitFactory
import com.example.speedoapp.model.BalanceResponse
import com.example.speedoapp.model.LoginStatus
import com.example.speedoapp.model.LogoutStatus
import com.example.speedoapp.model.Transaction
import com.example.speedoapp.navigation.AppRoutes.SIGNIN_ROUTE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class HomeViewModel : ViewModel() {
    private val _balance = MutableStateFlow<BalanceResponse>(
        BalanceResponse("", 0, "", "")
    )
    val balance = _balance.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions = _transactions.asStateFlow()

    private val _hasError = MutableStateFlow(0)
    val hasError = _hasError.asStateFlow()

    init {
        //getName()
        getBalance()
        //getTransactions()
    }

    private val _logoutStatus = MutableStateFlow<LogoutStatus?>(null)
    val logoutStatus: StateFlow<LogoutStatus?> = _logoutStatus.asStateFlow()
    private fun getBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitFactory.homeApi.getBalance()

                if (response.isSuccessful)
                    _balance.value = response.body() ?: BalanceResponse("", 0, "", "")
            } catch (e: Exception) {
                Log.d("API", "Exception: ${e.localizedMessage}")
                _hasError.value += 1
                Log.d("API", "Exception: ${e.stackTrace}")

            }
        }
    }

    //    fun getTransactions() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                _transactions.update {
//                    RetrofitFactory.homeApi.getTransactions().transactions
//
//                }
//            } catch (e: Exception) {
//                Log.d("trace", "Exception: ${e.localizedMessage}")
//                _hasError.value += 1
//            }
//        }
//    }
//
//    fun getName() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val body = RetrofitFactory.homeApi.getUser()
//                _name.value = body.name
//
//            } catch (e: Exception) {
//                Log.d("trace", "Exception: ${e.localizedMessage}")
//                _hasError.value += 1
//
//            }
//        }
//    }
//
    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitFactory.homeApi.logout()
                if (response.isSuccessful) {
                    val logoutResponse = response.body()
                    PreferencesManager.removeToken()
                    if (logoutResponse != null)
                        _logoutStatus.value = (LogoutStatus.Success(logoutResponse)) // Assuming you have a LogoutStatus sealed class
                } else {
                    _logoutStatus.value = (LogoutStatus.Error("Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                _logoutStatus.value = (LogoutStatus.Error("Exception: ${e.localizedMessage}"))
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

    fun balanceStringify(balance: Long): String {
        val formatter = DecimalFormat("#,###.00")
        return formatter.format(balance)
    }
}