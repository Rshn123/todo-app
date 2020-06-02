package com.example.loginpage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "todo_list")
data class Data(
        @ColumnInfo
        val title: String,
        @ColumnInfo
        val content: String = "",
        @PrimaryKey
        @ColumnInfo
        val id: String = UUID.randomUUID().toString().substring(0, 36),
        @ColumnInfo
        val isImportant: Boolean = false,
        @ColumnInfo
        val myDay: Boolean = false
)