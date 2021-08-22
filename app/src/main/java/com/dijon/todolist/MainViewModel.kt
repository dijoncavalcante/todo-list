package com.dijon.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dijon.todolist.model.data.Task
import com.dijon.todolist.model.repository.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val _allTasks: LiveData<List<Task>> = taskRepository.tasks
    val allTasks: LiveData<List<Task>> get() = _allTasks

    fun save(task: Task) {
        viewModelScope.launch {
            taskRepository.save(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            taskRepository.delete(task)
        }
    }
}