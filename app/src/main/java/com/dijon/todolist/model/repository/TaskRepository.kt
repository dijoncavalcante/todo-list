package com.dijon.todolist.model.repository

import com.dijon.todolist.model.dao.TaskDao
import com.dijon.todolist.model.data.Task

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun getAll(): List<Task> =
        taskDao.getAll()

    suspend fun save(task: Task) {s
        taskDao.save(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(id: Long) {
        taskDao.remove(id)
    }

    fun get(task: Task): Task =
        taskDao.get(task.id)
}