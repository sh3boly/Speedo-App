package com.example.speedoapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransferDao {
    @Insert
    suspend fun insertTransfer(transfer: TransferEntity)

    @Query("SELECT * FROM transfers")
    fun getAllTransfers(): Flow<List<TransferEntity>>
}
