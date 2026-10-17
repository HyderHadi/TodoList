package xyz.hyderhadi.todolist.domain.repository

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.todolist.domain.model.TodoItem

interface TodoRepository {

    fun getAllTodoItems(): Flow<List<TodoItem>>

    suspend fun saveTodoItem(todoItem: TodoItem)

    suspend fun deleteTodoItem(todoItem: TodoItem)
}