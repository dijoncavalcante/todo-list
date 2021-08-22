package com.dijon.todolist.model.repository

import androidx.lifecycle.LiveData
import com.dijon.todolist.model.dao.TaskDao
import com.dijon.todolist.model.data.Task

class TaskRepository(private val taskDao: TaskDao) {

    val tasks: LiveData<List<Task>>
        get() = taskDao.getAll()

    suspend fun save(task: Task) {
        taskDao.save(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.remove(task)
    }

    fun get(task: Task): Task =
        taskDao.get(task.id)
}