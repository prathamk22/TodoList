package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoListDao {

    @Insert
    suspend fun insert(item : TodoListEntity)

    @Delete
    suspend fun delete(item: TodoListEntity)

    @Update
    suspend fun update(item: TodoListEntity)

    @Query("SELECT * FROM TodoListEntity ORDER BY title ASC")
    fun getList() : LiveData<List<TodoListEntity>>

    @Query("SELECT * FROM TodoListEntity WHERE id = :id")
    fun getById(id: Int) : LiveData<TodoListEntity>
}