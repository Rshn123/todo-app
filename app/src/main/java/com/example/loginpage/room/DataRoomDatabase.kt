package com.example.loginpage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class DataRoomDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object {
        private var INSTANCE: DataRoomDatabase? = null

        fun getDatabase(context: Context): DataRoomDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataRoomDatabase::class.java,
                        "data-database"
                )
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE!!
        }
    }
}