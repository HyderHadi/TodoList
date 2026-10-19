package xyz.hyderhadi.todolist.ui

import xyz.hyderhadi.todolist.domain.model.TodoItem

sealed class TodoListEvents {
    data class DeleteTodoItem(val todoItem: TodoItem): TodoListEvents()
    object SaveTodoItem: TodoListEvents()
}