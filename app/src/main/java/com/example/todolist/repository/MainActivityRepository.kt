package com.example.todolist.repository

import com.example.todolist.database.TodoListDao
import com.example.todolist.database.TodoListEntity

class MainActivityRepository (
    private val todoListDao: TodoListDao
){

    fun fetchTodolist() = todoListDao.getList()

    suspend fun removeTodo(item: TodoListEntity) = todoListDao.delete(item)

}