package com.example.todolist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.AppDatabase
import com.example.todolist.database.TodoListEntity
import com.example.todolist.repository.MainActivityRepository
import com.example.todolist.repository.TodoEditRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class TodoEditViewModel(application: Application) : AndroidViewModel(application) {

    private val repo : TodoEditRepository

    init {
        val todoListDao = AppDatabase.getDatabase(application).todolistDao()
        repo = TodoEditRepository(todoListDao)
    }

    fun getTodo(id: Int) = repo.getTodo(id)

    fun addTodo(title: String, data: String){
        viewModelScope.launch {
            val item = TodoListEntity(
                0,
                title,
                data,
                SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date()))
            repo.addTodo(item)
        }
    }

}