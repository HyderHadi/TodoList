package xyz.hyderhadi.todolist.domain.use_case

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.todolist.domain.model.TodoItem
import xyz.hyderhadi.todolist.domain.repository.TodoRepository

class GetAllTodoItemsUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke(): Flow<List<TodoItem>> {
        return repository.getAllTodoItems()
    }
}