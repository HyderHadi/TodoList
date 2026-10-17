package xyz.hyderhadi.todolist.domain.use_case

data class TodoListUseCase(
    val addTodoItemUseCase: AddTodoItemUseCase,
    val deleteTodoItemUseCase: DeleteTodoItemUseCase,
    val getAllTodoItemsUseCase: GetAllTodoItemsUseCase
)
