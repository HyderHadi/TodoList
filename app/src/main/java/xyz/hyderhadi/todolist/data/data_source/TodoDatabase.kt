package xyz.hyderhadi.todolist.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.hyderhadi.todolist.domain.model.TodoItem

@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {

    abstract val todoDao: TodoDao

    companion object {
        const val DATABASE_NAME = "todo_db"
    }
}