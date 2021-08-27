package com.dijon.todolist.di

import android.app.Application
import com.dijon.todolist.di.daoModule
import com.dijon.todolist.di.repositoryModel
import com.dijon.todolist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

const val TAG = "AppApplication"

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule)
            modules(repositoryModel)
            modules(daoModule)
        }
    }
}