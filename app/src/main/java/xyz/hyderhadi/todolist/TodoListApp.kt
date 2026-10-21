package xyz.hyderhadi.todolist

import android.app.Application
import xyz.hyderhadi.todolist.di.AppModule
import xyz.hyderhadi.todolist.di.AppModuleImpl


class TodoListApp: Application() {
    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}