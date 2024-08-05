package com.example.speedoapp.constants

object Constants {
    const val NAME = "name"
    const val EMAIL = "email"
    const val PASSWORD = "password"
    const val TRANSFER_FROM = "transfer_from"
    const val TRANSFER_TO = "transfer_to"
    const val RECIPIENT = "recipient"
    const val RECIPIENT_NAME = "recipient_name"
    const val RECIPIENT_ACCOUNT_NUMBER = "recipient_account_number"
    const val AMOUNT = "amount"
    const val COUNTRY_CODE = "currency_code"
    const val COUNTRY_EXCHANGE_RATE = "exchange_rate"
    const val COUNTRY_FLAG_IMAGE = "flag_url"
    const val COUNTRY_NAME = "currency_name"
    const val CURRENCIES_ENDPOINT = "/api/currencies" //to be modified
    const val TRANSFER_ENDPOINT = "/api/transfer"
    const val BASE_URL = "http://10.0.2.2:3000" //to be modified
    const val IDENTIFIER = "identifier"
    const val INACTIVITY_TIME: Long = 3600000 / 2
}