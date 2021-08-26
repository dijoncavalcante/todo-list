package com.dijon.todolist.model.dao

import androidx.room.*
import com.dijon.todolist.model.data.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(task: Task)

    @Query("DELETE FROM table_task WHERE id = :id")
    suspend fun remove(id: Long)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM table_task")
    suspend fun getAll(): List<Task>

    @Query("SELECT * FROM table_task WHERE id = :key")
    fun get(key: Long): Task
}