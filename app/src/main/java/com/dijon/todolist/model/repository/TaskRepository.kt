package com.dijon.todolist.model.repository

import android.util.Log
import com.dijon.todolist.model.dao.TaskDao
import com.dijon.todolist.model.data.Task

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun getAll(): List<Task> =
        taskDao.getAll()

    suspend fun save(task: Task) {
        Log.d(TAG, "Task: title:${task.title} description:${task.description}")
        taskDao.save(task)
    }

    suspend fun update(task: Task) {
        Log.d(TAG, "Task: id:${task.id} title:${task.title} description:${task.description}")
        taskDao.update(task)
    }

    suspend fun delete(id: Long) {
        Log.d(TAG, "Task id: $id")
        taskDao.remove(id)
    }

    fun get(task: Task): Task =
        taskDao.get(task.id)

    companion object {
        const val TAG = "TaskRepository"
    }
}