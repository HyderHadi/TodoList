package xyz.hyderhadi.todolist.ui

import xyz.hyderhadi.todolist.domain.model.TodoItem

data class TodoListUiState(
    val todoItems: List<TodoItem> = emptyList(),
    val isDone: Boolean = false,
    val todoText: String = ""
)
