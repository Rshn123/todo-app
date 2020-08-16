package com.example.loginpage.room

import androidx.lifecycle.LiveData

class DataRepository(private val dataDao: DataDao) {
    val allData: LiveData<List<Data>> = dataDao.getAll()
    val allMyData: LiveData<List<Data>> = dataDao.getAllMyDay()
    val allImportantData: LiveData<List<Data>> = dataDao.getAllImportant()
    fun insert(data: Data) {
        dataDao.insertTodo(data)
    }

    fun delete(id: String) {
        dataDao.delete(id)
    }

    fun getTableRod(id: String): Data {
        return dataDao.getTableRow(id)
    }

    fun updateReminder(id:String, reminder:Boolean){
        dataDao.updateReminder(id, reminder)
    }

    fun updateImportant(id: String, boolean: Boolean) {
        dataDao.updateIsImportant(id, boolean)
    }

    fun updateMyDay(id: String, boolean: Boolean) {
        dataDao.updateMYDay(id, boolean)
    }

    companion object {
        private var INSTANCE: DataRepository? = null

        fun getInstance(dataDao: DataDao): DataRepository {
            if (INSTANCE == null) {
                INSTANCE = DataRepository(dataDao)
            }
            return INSTANCE!!
        }
    }
}