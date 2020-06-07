package com.example.todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TodoListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val data: String,
    val date: String
)