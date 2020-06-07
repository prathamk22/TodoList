package com.example.todolist.repository

import com.example.todolist.database.TodoListDao
import com.example.todolist.database.TodoListEntity

class TodoEditRepository(
    private val todoListDao: TodoListDao
) {

    fun getTodo(id: Int) = todoListDao.getById(id)

    suspend fun addTodo(item: TodoListEntity){
        todoListDao.insert(item)
    }

}