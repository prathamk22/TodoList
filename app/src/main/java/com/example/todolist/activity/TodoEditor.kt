package com.example.todolist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.viewmodels.TodoEditViewModel
import kotlinx.android.synthetic.main.activity_todo_editor.*

class TodoEditor : AppCompatActivity() {

    var id: Int = -1
    lateinit var vm: TodoEditViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_editor)
        vm = ViewModelProvider(this).get(TodoEditViewModel::class.java)
        id = intent.getIntExtra("id", -1)

        if (id!= -1){
            vm.getTodo(id).observe(this, Observer{
                titleEdit.setText(it.title)
                descriptionEdit.setText(it.data)
            })
        }

        back.setOnClickListener {
            finish()
        }

        save.setOnClickListener {
            if (!titleEdit.text.isNullOrEmpty() and !descriptionEdit.text.isNullOrEmpty()){
                vm.addTodo(titleEdit.text.toString(), descriptionEdit.text.toString())
            }
        }

    }
}