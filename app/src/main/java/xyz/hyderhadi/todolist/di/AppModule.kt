package xyz.hyderhadi.todolist.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.hyderhadi.todolist.data.data_source.TodoDatabase
import xyz.hyderhadi.todolist.data.repository.OfflineTodoRepositoryImpl
import xyz.hyderhadi.todolist.domain.repository.TodoRepository
import xyz.hyderhadi.todolist.domain.use_case.AddTodoItemUseCase
import xyz.hyderhadi.todolist.domain.use_case.DeleteTodoItemUseCase
import xyz.hyderhadi.todolist.domain.use_case.GetAllTodoItemsUseCase
import xyz.hyderhadi.todolist.domain.use_case.TodoListUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = TodoDatabase::class.java,
            name = TodoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return OfflineTodoRepositoryImpl(db.todoDao)
    }

    @Provides
    @Singleton
    fun provideTodoUseCase(repository: TodoRepository): TodoListUseCase {
        return TodoListUseCase(
            addTodoItemUseCase = AddTodoItemUseCase(repository),
            deleteTodoItemUseCase = DeleteTodoItemUseCase(repository),
            getAllTodoItemsUseCase = GetAllTodoItemsUseCase(repository)
        )
    }
}