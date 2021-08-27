package com.dijon.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dijon.todolist.model.data.Task
import com.dijon.todolist.model.repository.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _allTasks = MutableLiveData<List<Task>>()
    val allTasks: LiveData<List<Task>> get() = _allTasks

    fun getAllTasks() = viewModelScope.launch {
        _allTasks.postValue(taskRepository.getAll())
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            taskRepository.delete(id)
            getAllTasks()
        }
    }
}


