package com.example.speedoapp.model

data class TransactionRoot(val transactions: List<Transaction>)
data class Transaction(
    val name: String,
    val type: String,
    val amount: Float,
    val date: String,
)