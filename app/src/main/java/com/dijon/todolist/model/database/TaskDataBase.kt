package com.dijon.todolist.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dijon.todolist.model.dao.TaskDao
import com.dijon.todolist.model.data.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDataBase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {

        @Volatile
        private var INSTANCE: TaskDataBase? = null

        fun getInstance(context: Context): TaskDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDataBase::class.java,
                        "task_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}