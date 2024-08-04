package com.example.speedoapp.ui.tranfer


import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delasign.samplestarterproject.utils.readJsonFromAssets
import com.example.speedoapp.NotificationService
import com.example.speedoapp.api.CurrencyApiService
import com.example.speedoapp.api.RetrofitFactory
import com.example.speedoapp.model.Currency
import com.example.speedoapp.model.ExchangeRate
import com.example.speedoapp.model.Recipient
import com.example.speedoapp.model.TransferData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

class AmountScreenViewModel : ViewModel() {
    // This list will hold all the currencies received from the backend
    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies = _currencies.asStateFlow()

    private val _chosenImageFrom = MutableStateFlow("")
    val imageFrom = _chosenImageFrom.asStateFlow()

    private val _chosenImageTo = MutableStateFlow("")
    val imageTo = _chosenImageTo.asStateFlow()

    private val _exchangeRateText = MutableStateFlow("")
    val exchangeRateText = _exchangeRateText.asStateFlow()

    private val _transferResult = MutableStateFlow<Boolean?>(null)
    val transferResult = _transferResult.asStateFlow()


    // Here we save the latest data chosen by the user from money and receiver data
    private val _transferData = MutableStateFlow(
        TransferData(
            from = ExchangeRate(
                "",
                "",
                ""
            ),
            to = ExchangeRate(
                "", "", ""
            ),
            recipient = Recipient(
                "", ""
            )
        )
    )
    val transferData = _transferData.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError = _hasError.asStateFlow()

    private fun updateImageFrom(imageUrl: String) {
        _chosenImageFrom.value = imageUrl
    }

    private fun updateImageTo(imageUrl: String) {
        _chosenImageTo.value = imageUrl
    }

    fun reset() {
        updateCurrencyFrom(_currencies.value[0])
        updateCurrencyTo(_currencies.value[1])
        updateAmountFrom("1000")
        updateRecipientName("")
        updateRecipientAccountNumber("")
    }

    // Here we update the value that should be sent from user account and updates the equivalent value that the recipient gets
    fun updateAmountFrom(amount: String) {
        _transferData.update { current ->
            val updatedFrom = current.from.copy(amount = amount.toString())
            val updatedToAmount =
                exchangeRateCalculator(amount, updatedFrom.currency, current.to.currency)
            current.copy(
                from = updatedFrom, to = current.to.copy(amount = updatedToAmount.toString())
            )
        }
    }

    fun updateAmountTo(amount: String) {
        _transferData.update { current ->
            val updatedTo = current.to.copy(amount = amount.toString())
            val updatedFromAmount =
                exchangeRateCalculator(amount, updatedTo.currency, current.from.currency)
            current.copy(
                to = updatedTo, from = current.from.copy(amount = updatedFromAmount.toString())
            )
        }
    }

    fun updateCurrencyFrom(currency: Currency) {
        updateImageFrom(currency.imageUrl)
        _transferData.update { current ->
            val updatedFrom =
                current.from.copy(currency = currency.code, exchangeRate = currency.exchangeRate)

            val updatedToAmount = exchangeRateCalculator(
                current.from.amount,
                currency.code,
                current.to.currency
            )
            current.copy(
                from = updatedFrom, to = current.to.copy(amount = updatedToAmount.toString())
            )
        }
        updateExchangeRateText(_transferData.value.from.currency, _transferData.value.to.currency)
    }

    fun updateCurrencyTo(currency: Currency) {
        updateImageTo(currency.imageUrl)

        _transferData.update { current ->
            val updatedTo =
                current.to.copy(currency = currency.code, exchangeRate = currency.exchangeRate)

            val updatedToAmount = exchangeRateCalculator(
                current.from.amount,
                current.from.currency,
                currency.code
            )
            current.copy(to = updatedTo.copy(amount = updatedToAmount.toString()))
        }

        updateExchangeRateText(_transferData.value.from.currency, _transferData.value.to.currency)
    }

    fun updateRecipientName(name: String) {
        _transferData.update { current ->
            current.copy(recipient = current.recipient.copy(name = name))
        }
    }

    fun updateRecipientAccountNumber(accountNumber: String) {
        _transferData.update { current ->
            current.copy(recipient = current.recipient.copy(accountNumber = accountNumber))
        }
    }

    private fun updateExchangeRateText(currencyFrom: String, currencyTo: String) {
        _exchangeRateText.value =
            exchangeRateCalculator("1", currencyFrom, currencyTo).toString()
    }

    private fun exchangeRateCalculator(
        amount: String, currencyFrom: String, currencyTo: String
    ): String {
        if (amount.isEmpty())
            return ""
        val amountCalc = amount.toBigDecimalOrNull() ?: BigDecimal.ZERO
        if (currencyFrom == currencyTo) {
            return amount
        }
        val exchangeRateFromUSDToFrom =
            currencies.value.find { it.code == currencyFrom }?.exchangeRate?.toBigDecimal()
                ?: BigDecimal.ONE
        val exchangeRateFromUSDToTo =
            currencies.value.find { it.code == currencyTo }?.exchangeRate?.toBigDecimal()
                ?: BigDecimal.ONE

        return if (currencyFrom == "USD") {
            amountCalc.multiply(exchangeRateFromUSDToTo).setScale(2, RoundingMode.HALF_EVEN)
                .toString()
        } else if (currencyTo == "USD") {
            amountCalc.divide(exchangeRateFromUSDToFrom, 2, RoundingMode.HALF_EVEN).toString()
        } else {
            // Convert from the source currency to USD and then to the target currency
            amountCalc.divide(exchangeRateFromUSDToFrom, 2, RoundingMode.HALF_EVEN)
                .multiply(exchangeRateFromUSDToTo).setScale(2, RoundingMode.HALF_EVEN).toString()
        }
    }

    fun validateData(): Boolean {
        val valueFrom = _transferData.value.from.amount.toBigDecimalOrNull()

        return (valueFrom != null && valueFrom < 5000.toBigDecimal()
                && _transferData.value.from.amount.isNotEmpty()
                && _transferData.value.recipient.name.isNotEmpty()
                && _transferData.value.recipient.accountNumber.isNotEmpty()
                && _transferData.value.to.amount != "0")
    }

    fun amountValidation(): String? {
        val amount = _transferData.value.from.amount.toBigDecimalOrNull()
        if (amount == null)
            return amount
        return if (amount > 5000.toBigDecimal()) {
            "There is limit 5000 per transaction"
        } else {
            null
        }
    }

    fun accountNumberValidation(): String? {
        val accountNumber = _transferData.value.recipient.accountNumber
        return if (accountNumber.length != 16)
            "Please enter valid account number"
        else
            null
    }

    private fun fetchCurrencies() {

        viewModelScope.launch {
            try {
                val response = CurrencyApiService.callable.getCurrencies()

                if (response.isSuccessful) {
                    response.body()?.let { currencyList ->
                        _currencies.update { currencyList }
                        if (currencyList.isNotEmpty()) {
                            updateCurrencyFrom(currencyList[0])
                            updateCurrencyTo(currencyList[1])
                            updateAmountFrom("1000")
                        }
                        _hasError.value = false
                    } ?: run {
                        _hasError.value = true
                    }
                } else {
                    _hasError.value = true
                }
            } catch (e: Exception) {
                _hasError.value = true
            }
        }
    }

    private suspend fun postTransferData(): Boolean {
        return try {
            val response = RetrofitFactory.transferApi.postTransfer(_transferData.value)
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    fun confirmTransfer(context: Context) {
        viewModelScope.launch {
            val transferSuccessful = postTransferData()

            _transferResult.value = transferSuccessful

            sendNotification(context, transferSuccessful)
        }
    }

    private fun sendNotification(context: Context, transferSuccessful: Boolean) {
        val service = NotificationService(context)
        if (transferSuccessful) {
            service.showNotification("Your transfer was successful")
        } else {
            service.showNotification("Your transfer failed")
        }
    }


    fun loadCurrenciesFromLocal(context: Context) {
        viewModelScope.launch {
            val jsonString = readJsonFromAssets(context, "currencies.json")
            val currencyType = object : TypeToken<List<Currency>>() {}.type
            val currencyList = Gson().fromJson<List<Currency>>(jsonString, currencyType)

            if (currencyList != null) {
                _currencies.update { currencyList }
                if (currencyList.isNotEmpty()) {
                    updateCurrencyFrom(currencyList[0])
                    updateCurrencyTo(currencyList[1])
                    updateAmountFrom("1000")
                }
                _hasError.value = false
            } else {
                Log.e("DATA_ERROR", "Failed to parse currency data")
                _hasError.value = true
            }
        }
    }

}