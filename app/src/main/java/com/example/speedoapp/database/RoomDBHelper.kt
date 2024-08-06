package com.example.speedoapp.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [TransferEntity::class], version = 1)
abstract class RoomDBHelper : RoomDatabase() {
    abstract fun transferDao(): TransferDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDBHelper? = null

        fun getDatabase(context: Context): RoomDBHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDBHelper::class.java,
                    "speedo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
