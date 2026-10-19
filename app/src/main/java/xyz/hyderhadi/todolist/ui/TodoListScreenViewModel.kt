package xyz.hyderhadi.todolist.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import xyz.hyderhadi.todolist.domain.model.TodoItem
import xyz.hyderhadi.todolist.domain.use_case.TodoListUseCase
import javax.inject.Inject


@HiltViewModel
class TodoListScreenViewModel @Inject constructor(
    private val todoListUseCase: TodoListUseCase
): ViewModel() {


    var state = mutableStateOf(TodoListUiState())
        private set

    init {
        todoListUseCase.getAllTodoItemsUseCase()
            .onEach {
                state.value = state.value.copy(
                    todoItems = it
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvents(event: TodoListEvents) {
        when(event) {
            is TodoListEvents.SaveTodoItem -> {
                viewModelScope.launch {
                    todoListUseCase.addTodoItemUseCase(
                        TodoItem(
                            todoText = state.value.todoText,
                            isDone = state.value.isDone
                        )
                    )
                }
            }
            is TodoListEvents.DeleteTodoItem -> {
                viewModelScope.launch {
                    todoListUseCase.deleteTodoItemUseCase(event.todoItem)
                }
            }
        }
    }

    fun updateTodoItemStatus(todoItem: TodoItem) {
        viewModelScope.launch {
            todoListUseCase.addTodoItemUseCase(
                todoItem.copy(
                    isDone = !todoItem.isDone
                )
            )
        }
    }

    fun updateTodoItemText(todoText: String) {
        state.value = state.value.copy(
            todoText = todoText
        )
    }
}