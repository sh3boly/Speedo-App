package com.example.speedoapp.ui.homepage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedoapp.api.RetrofitFactory
import com.example.speedoapp.model.BalanceResponse
import com.example.speedoapp.model.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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

    private fun getBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("API", "before api call")
                val response = RetrofitFactory.homeApi.getBalance()
                Log.d("API", "after: ${response.body()?.name}")

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
//    fun logout(){
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                RetrofitFactory.homeApi.logout()
//            }
//            catch (e: Exception){
//                Log.d("trace", "Exception: ${e.localizedMessage}")
//            }
//        }
//    }

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