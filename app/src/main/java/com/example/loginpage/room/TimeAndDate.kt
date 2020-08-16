package com.example.loginpage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Month
import java.util.*

@Entity(tableName = "TimeAndDate")
data class TimeAndDate(
    @PrimaryKey
    @ColumnInfo
    val id: String = UUID.randomUUID().toString().substring(0, 36),
    @ColumnInfo
    val year: Int = 0,
    @ColumnInfo
    val month: Int = 0,
    @ColumnInfo
    val day: Int = 0,
    @ColumnInfo
    val hour: Int = 0,
    @ColumnInfo
    val minute: Int = 0
)