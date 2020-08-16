package com.example.loginpage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todo_list", indices = arrayOf(Index(value = ["id"], unique = true)))
data class Data(
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val content: String = "",
    @ColumnInfo
    val id: String = UUID.randomUUID().toString().substring(0, 36),
    @ColumnInfo
    val isImportant: Boolean = false,
    @ColumnInfo
    val myDay: Boolean = false,
    @ColumnInfo
    val reminder: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo()
    val number: Int = 0
)