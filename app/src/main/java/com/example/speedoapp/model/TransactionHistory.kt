package com.example.speedoapp.model
data class TransactionHistoryRoot(val transactions: List<TransactionHistory>)

data class TransactionHistory(
    val name: String,
    val cardType: String,
    val cardNumber: String,
    val transactionDate: String,
    val status: String,
    val amount: Double
)
