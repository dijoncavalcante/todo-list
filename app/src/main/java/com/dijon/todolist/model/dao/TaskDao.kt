package com.dijon.todolist.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dijon.todolist.model.data.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(task: Task)

    @Delete
    suspend fun remove(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM table_task")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM table_task WHERE id = :key")
    fun get(key: Long)
}