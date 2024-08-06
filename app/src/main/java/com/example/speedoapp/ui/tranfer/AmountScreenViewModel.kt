package com.example.speedoapp.ui.tranfer


import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedoapp.NotificationService
import com.example.speedoapp.api.CurrencyApiService
import com.example.speedoapp.api.RetrofitFactory
import com.example.speedoapp.database.RoomDBHelper
import com.example.speedoapp.database.TransferEntity
import com.example.speedoapp.model.BalanceResponse
import com.example.speedoapp.model.Currency
import com.example.speedoapp.model.ExchangeRate
import com.example.speedoapp.model.Favourite
import com.example.speedoapp.model.FavouriteRequest
import com.example.speedoapp.model.Recipient
import com.example.speedoapp.model.TransferData
import com.example.speedoapp.utils.readJsonFromAssets
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

class AmountScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val transferDao = RoomDBHelper.getDatabase(application).transferDao()

    init {
        getBalance()
        getFavourites()
    }

    private fun saveTransferData(transferData: TransferData) {
        viewModelScope.launch {
            val transferEntity = TransferEntity.fromTransferData(transferData)
            transferDao.insertTransfer(transferEntity)
        }
    }

    private val _favourites = MutableStateFlow<List<Favourite>>(emptyList())
    val favourites = _favourites.asStateFlow()

    // This list will hold all the currencies received from the json file stored in assets
    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies = _currencies.asStateFlow()

    private val _balance = MutableStateFlow<BalanceResponse>(
        BalanceResponse("", 0, "", "")
    )
    val balance = _balance.asStateFlow()

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

    private fun updateExchangeRateText(currencyFrom: String, currencyTo: String) {
        _exchangeRateText.value =
            exchangeRateCalculator("1", currencyFrom, currencyTo)
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
            amountCalc.divide(exchangeRateFromUSDToFrom, 2, RoundingMode.HALF_EVEN)
                .multiply(exchangeRateFromUSDToTo).setScale(2, RoundingMode.HALF_EVEN).toString()
        }
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

    private fun sendNotification(context: Context, transferSuccessful: Boolean) {
        val service = NotificationService(context)
        if (transferSuccessful) {
            service.showNotification("Your transfer was successful")
        } else {
            service.showNotification("Your transfer failed")
        }
    }

    private fun getBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitFactory.homeApi.getBalance()

                if (response.isSuccessful)
                    _balance.value = response.body() ?: BalanceResponse("", 0, "", "")
            } catch (e: Exception) {
                _hasError.value = false

            }
        }
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

    fun confirmTransferInDb(context: Context) {
        viewModelScope.launch {
            _transferResult.value = true
            saveTransferData(_transferData.value)
            sendNotification(context, _transferResult.value!!)
        }
    }

    fun logAllTransfers() {
        viewModelScope.launch {
            transferDao.getAllTransfers().collect { transfers ->
                transfers.forEach { transfer ->
                    println("Transfer ID: ${transfer.id}, From: ${transfer.fromAmount} ${transfer.fromCurrency}, To: ${transfer.toAmount} ${transfer.toCurrency}, Recipient: ${transfer.recipientName}, Account: ${transfer.recipientAccountNumber}")
                }
            }
        }
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
            val updatedFrom = current.from.copy(amount = amount)
            val updatedToAmount =
                exchangeRateCalculator(amount, updatedFrom.currency, current.to.currency)
            current.copy(
                from = updatedFrom, to = current.to.copy(amount = updatedToAmount)
            )
        }
    }

    fun updateAmountTo(amount: String) {
        _transferData.update { current ->
            val updatedTo = current.to.copy(amount = amount)
            val updatedFromAmount =
                exchangeRateCalculator(amount, updatedTo.currency, current.from.currency)
            current.copy(
                to = updatedTo, from = current.from.copy(amount = updatedFromAmount)
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
                from = updatedFrom, to = current.to.copy(amount = updatedToAmount)
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
            current.copy(to = updatedTo.copy(amount = updatedToAmount))
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

    fun validateData(): Boolean {
        val valueFrom = _transferData.value.from.amount.toBigDecimalOrNull()

        return (valueFrom != null && valueFrom < 5000.toBigDecimal()
                && valueFrom <= _balance.value.balance.toBigDecimal()
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
        } else if (amount > _balance.value.balance.toBigDecimal()) {
            "Not enough balance"
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

    fun loadFavouritesFromLocal(context: Context) {
        viewModelScope.launch {
            val jsonString = readJsonFromAssets(context, "favourites.json")
            val favouriteType = object : TypeToken<List<Favourite>>() {}.type

            val favouriteList = Gson().fromJson<List<Favourite>>(jsonString, favouriteType)
            if (favouriteList != null) {
                _favourites.update { favouriteList }
                _hasError.value = false
            } else {

                _hasError.value = true
            }
        }
    }

    fun addFavourite(recipientName: String, recipientAccountNumber: String) {
        val favourite = FavouriteRequest(recipientName, recipientAccountNumber)
        viewModelScope.launch {
            try {
                val response = RetrofitFactory.favouriteApi.addFavourite(favourite)
                if (response.isSuccessful) {
                    getFavourites()
                } else {
                    _hasError.value = true
                }
            } catch (e: Exception) {
                _hasError.value = true
            }

        }
    }

    fun confirmTransfer(context: Context) {
        viewModelScope.launch {
            val transferSuccessful = postTransferData()

            _transferResult.value = transferSuccessful

            sendNotification(context, transferSuccessful)
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
                _hasError.value = true
            }
        }
    }

    fun searchRecipient(name: String, accountNumber: String): Boolean {
        if (_favourites.value.isEmpty()) return false

        return _favourites.value.any { favourite ->
            favourite.recipientName.equals(name, ignoreCase = true) &&
                    favourite.recipientAccountNumber.equals(accountNumber, ignoreCase = true)
        }
    }

}