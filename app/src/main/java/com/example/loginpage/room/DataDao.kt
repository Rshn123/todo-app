package com.example.loginpage.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.loginpage.room.Data

@Dao
interface DataDao {
    @Query("SELECT * FROM todo_list")
    fun getAll(): LiveData<List<Data>>

    @Query("DELETE FROM todo_list WHERE id = :id")
    fun delete(id: String)

    @Query("SELECT * FROM todo_list WHERE id = :id")
    fun getTableRow(id: String): Data

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTodo(todo: Data)

    @Query("UPDATE todo_list SET isImportant = :isImportant WHERE id= :id")
    fun updateIsImportant(id: String, isImportant: Boolean)

    @Query("UPDATE todo_list SET myDay = :myDay WHERE id = :id")
    fun updateMYDay(id: String, myDay: Boolean)

    @Query("SELECT * FROM todo_list WHERE isImportant = 1")
    fun getAllImportant(): LiveData<List<Data>>

    @Query("SELECT * FROM todo_list WHERE myDay = 1")
    fun getAllMyDay(): LiveData<List<Data>>

}