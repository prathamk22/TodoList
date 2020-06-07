package com.example.todolist.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.activity.TodoEditor
import com.example.todolist.database.TodoListEntity
import kotlinx.android.synthetic.main.item_layout.view.*

class MainListAdapter : ListAdapter<TodoListEntity, MainViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class DiffCallback : DiffUtil.ItemCallback<TodoListEntity>() {
    override fun areItemsTheSame(oldItem: TodoListEntity, newItem: TodoListEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoListEntity, newItem: TodoListEntity): Boolean {
        return oldItem.equals(newItem)
    }
}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var clickListener: View.OnClickListener? = null

    fun bind(item : TodoListEntity) {
        with(itemView){
            with(item){
                titleTv.text = title
                descriptionTv.text = data
                addedOn.text = date
                setOnClickListener {
                    val intent = Intent(titleTv.context, TodoEditor::class.java)
                    intent.putExtra("id", item.id)
                    titleTv.context.startActivity(intent)
                }
            }
        }
    }
}