package xyz.hyderhadi.todolist.data.repository

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.todolist.data.data_source.TodoDao
import xyz.hyderhadi.todolist.domain.model.TodoItem
import xyz.hyderhadi.todolist.domain.repository.TodoRepository


class OfflineTodoRepositoryImpl(
    private val todoDao: TodoDao
): TodoRepository {

    override fun getAllTodoItems(): Flow<List<TodoItem>> {
        return todoDao.getAllTodoItems()
    }

    override suspend fun saveTodoItem(todoItem: TodoItem) {
        todoDao.insert(todoItem)
    }

    override suspend fun deleteTodoItem(todoItem: TodoItem) {
        todoDao.delete(todoItem)
    }
}

