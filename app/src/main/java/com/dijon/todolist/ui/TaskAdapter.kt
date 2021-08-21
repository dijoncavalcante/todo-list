package com.dijon.todolist.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dijon.todolist.databinding.ItemTaskBinding
import com.dijon.todolist.model.data.Task

const val TAG = "TaskAdapter"

class TaskAdapter(private val tasks: List<Task>) :
    RecyclerView.Adapter<TaskAdapter.TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        Log.d(TAG, "[onCreateViewHolder]")
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemTaskBinding.inflate(inflater, parent, false)
        return TasksViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bindView(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    inner class TasksViewHolder(private val itemBinding: ItemTaskBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindView(task: Task) {
            task.title.also { itemBinding.tvTitle.text = it }
            "${task.date}  ${task.hour}".also { itemBinding.tvDate.text = it }
        }
    }
}