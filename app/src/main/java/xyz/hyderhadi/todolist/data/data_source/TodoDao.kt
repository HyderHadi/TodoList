package xyz.hyderhadi.todolist.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.todolist.domain.model.TodoItem

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todoItem: TodoItem)

    @Delete
    suspend fun delete(todoItem: TodoItem)

    @Query("SELECT * FROM todo_items")
    fun getAllTodoItems(): Flow<List<TodoItem>>
}