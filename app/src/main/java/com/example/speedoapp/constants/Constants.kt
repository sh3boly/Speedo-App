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
    const val CURRENCIES_ENDPOINT = "api/currencies" //to be modified
    const val TRANSFER_ENDPOINT = "api/transfer"
    const val ADD_FAVOURITE_ENDPOINT = "api/favorites/addFavorite"
    const val DELETE_FAVOURITE_ENDPOINT = "api/favorites/delete/{id}"
    const val UPDATE_FAVOURITE_ENDPOINT = "api/favorites/updateFavorite/{id}"
    const val GET_FAVOURITE_ENDPOINT = "api/favorites/getFavorites"
    const val BASE_URL = "http://bm-app-env.eba-yspz5pwf.eu-north-1.elasticbeanstalk.com/" //to be modified
    const val IDENTIFIER = "identifier"
    const val FAVOURITE_RECIPIENT_ID = "id"
    const val FAVOURITE_RECIPIENT_NAME = "recipientName"
    const val FAVOURITE_RECIPIENT_ACCOUNT_NUMBER = "recipientAccountId"
    const val INACTIVITY_TIME: Long = 3600000 / 2
}