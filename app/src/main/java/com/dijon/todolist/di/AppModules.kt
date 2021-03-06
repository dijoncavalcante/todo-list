package com.dijon.todolist.di

import com.dijon.todolist.ui.AddTaskViewModel
import com.dijon.todolist.ui.MainViewModel
import com.dijon.todolist.model.database.TaskDataBase
import com.dijon.todolist.model.repository.TaskRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { AddTaskViewModel(get()) }
}

val repositoryModel = module {
    single { TaskRepository(get()) }
}

val daoModule = module {
    single { TaskDataBase.getInstance(androidContext()).taskDao }
}

