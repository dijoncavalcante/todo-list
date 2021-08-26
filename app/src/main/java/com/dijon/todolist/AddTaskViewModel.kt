package com.dijon.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dijon.todolist.model.data.Task
import com.dijon.todolist.model.repository.TaskRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    fun save(task: Task) {
        viewModelScope.launch {
            taskRepository.save(task)
        }
    }

    fun update(task: Task) {
        viewModelScope.launch {
            taskRepository.update(task)
        }
    }
}