package xyz.hyderhadi.todolist.di

import android.content.Context
import androidx.room.Room
import xyz.hyderhadi.todolist.data.data_source.TodoDatabase
import xyz.hyderhadi.todolist.data.repository.OfflineTodoRepositoryImpl
import xyz.hyderhadi.todolist.domain.repository.TodoRepository
import xyz.hyderhadi.todolist.domain.use_case.AddTodoItemUseCase
import xyz.hyderhadi.todolist.domain.use_case.DeleteTodoItemUseCase
import xyz.hyderhadi.todolist.domain.use_case.GetAllTodoItemsUseCase
import xyz.hyderhadi.todolist.domain.use_case.TodoListUseCase


interface AppModule {
    val appDatabase: TodoDatabase
    val appRepository: TodoRepository
    val appUseCases: TodoListUseCase
}

class AppModuleImpl(
    private val appContext: Context
): AppModule {

    override val appDatabase: TodoDatabase by lazy {
        Room.databaseBuilder(
            context = appContext,
            klass = TodoDatabase::class.java,
            name = TodoDatabase.DATABASE_NAME
        ).build()
    }

    override val appRepository: TodoRepository by lazy {
        OfflineTodoRepositoryImpl(appDatabase.todoDao)
    }

    override val appUseCases: TodoListUseCase by lazy {
        TodoListUseCase(
            addTodoItemUseCase = AddTodoItemUseCase(appRepository),
            deleteTodoItemUseCase = DeleteTodoItemUseCase(appRepository),
            getAllTodoItemsUseCase = GetAllTodoItemsUseCase(appRepository)
        )
    }
}