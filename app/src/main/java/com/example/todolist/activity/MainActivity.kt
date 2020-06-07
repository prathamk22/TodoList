package com.example.todolist.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapters.MainListAdapter
import com.example.todolist.adapters.SwipeToDeleteCallback
import com.example.todolist.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity(){

    private lateinit var vm : MainActivityViewModel
    private val adapter: MainListAdapter = MainListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        recyclerView.adapter = adapter

        vm.fetchTodoList().observe(this, Observer {
            adapter.submitList(it)
        })

        val swipeToDeleteCallback = object : SwipeToDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                vm.removeTodo(adapter.currentList[viewHolder.adapterPosition])
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        add.setOnClickListener {
            val intent = Intent(this, TodoEditor::class.java)
            intent.putExtra("id", -1)
            startActivity(intent)
        }
    }
}