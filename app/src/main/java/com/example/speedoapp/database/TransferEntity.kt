package com.example.speedoapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.speedoapp.model.ExchangeRate
import com.example.speedoapp.model.Recipient
import com.example.speedoapp.model.TransferData

@Entity(tableName = "transfers")
data class TransferEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fromAmount: String,
    val fromCurrency: String,
    val fromExchangeRate: String,
    val toAmount: String,
    val toCurrency: String,
    val toExchangeRate: String,
    val recipientName: String,
    val recipientAccountNumber: String
) {
    companion object {
        fun fromTransferData(transferData: TransferData): TransferEntity {
            return TransferEntity(
                fromAmount = transferData.from.amount,
                fromCurrency = transferData.from.currency,
                fromExchangeRate = transferData.from.exchangeRate,
                toAmount = transferData.to.amount,
                toCurrency = transferData.to.currency,
                toExchangeRate = transferData.to.exchangeRate,
                recipientName = transferData.recipient.name,
                recipientAccountNumber = transferData.recipient.accountNumber
            )
        }
    }
}
