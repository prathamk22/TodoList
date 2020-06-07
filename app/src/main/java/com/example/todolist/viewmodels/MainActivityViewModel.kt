package com.example.todolist.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.todolist.database.AppDatabase
import com.example.todolist.database.TodoListEntity
import com.example.todolist.repository.MainActivityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class MainActivityViewModel(application: Application) : AndroidViewModel(application){

    private val repo: MainActivityRepository

    init {
        val todoListDao = AppDatabase.getDatabase(application).todolistDao()
        repo = MainActivityRepository(todoListDao)
    }

    fun fetchTodoList() = repo.fetchTodolist()

    fun removeTodo(item: TodoListEntity) = viewModelScope.launch {
        repo.removeTodo(item)
    }

}