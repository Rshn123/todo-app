package com.example.loginpage

import android.content.Context
import androidx.lifecycle.*
import com.example.loginpage.room.Data
import com.example.loginpage.room.DataRepository
import com.example.loginpage.room.DataRoomDatabase

class DataViewModel(context: Context) : ViewModel() {

    private val repository: DataRepository
    val allData: LiveData<List<Data>>
    val allMyDayData: LiveData<List<Data>>
    val allImportant: LiveData<List<Data>>

    init {
        repository = DataRepository.getInstance(DataRoomDatabase.getDatabase(context).dataDao())
        allData = repository.allData
        allMyDayData = repository.allMyData
        allImportant = repository.allImportantData
    }

    internal fun insert(data: Data) {
        repository.insert(data)
    }

    internal fun getTableRow(id: String): Data {
        return repository.getTableRod(id)
    }

    internal fun delete(id: String) {
        repository.delete(id)
    }

    internal fun updateReminder(id:String, reminder: Boolean){
        repository.updateReminder(id,reminder)
    }

    internal fun updateImportant(id: String, isImportant: Boolean) {
        repository.updateImportant(id, isImportant)
    }

    internal fun updateMyDay(id: String, isMyday: Boolean){
        repository.updateMyDay(id, isMyday)
    }

    class Factory internal constructor(ctxt: Context) : ViewModelProvider.Factory {
        private val ctxt: Context

        init {
            this.ctxt = ctxt.applicationContext
        }

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DataViewModel(ctxt) as T
        }
    }
}