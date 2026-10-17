package xyz.hyderhadi.todolist.domain.use_case

import xyz.hyderhadi.todolist.domain.model.TodoItem
import xyz.hyderhadi.todolist.domain.repository.TodoRepository

class AddTodoItemUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todoItem: TodoItem) {
        repository.saveTodoItem(todoItem)
    }
}