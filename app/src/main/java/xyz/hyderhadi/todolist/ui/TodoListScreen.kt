package xyz.hyderhadi.todolist.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import my.nanihadesuka.compose.LazyColumnScrollbar
import my.nanihadesuka.compose.ScrollbarSettings
import xyz.hyderhadi.todolist.TodoListApp
import xyz.hyderhadi.todolist.ui.components.TodoItem


@Composable
fun TodoListScreen(
    modifier: Modifier = Modifier,
    viewModel: TodoListScreenViewModel = viewModel(factory = viewModelFactory {
        TodoListScreenViewModel(TodoListApp.appModule.appUseCases)
    })
) {

    val state = viewModel.state.value
    var todoText by remember { mutableStateOf("") }
    var isTextFieldEmpty by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val anyTodoItemDone = state.todoItems.filter { it.isDone }
    val anyTodoItemActive = state.todoItems.filter { !it.isDone }
    val listStateForActiveTodos = rememberLazyListState()
    val listStateForDoneTodos = rememberLazyListState()

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = todoText,
                        onValueChange = {
                            todoText = it
                        },
                        placeholder = {
                            Text("Add an item")
                        },
                        maxLines = 1,
                        isError = isTextFieldEmpty,
                        modifier = Modifier.weight(1f)
                    )
                    FloatingActionButton(
                        onClick = {
                            if(todoText.isBlank()) {
                                isTextFieldEmpty = true
                                Toast.makeText(
                                    context,
                                    "Invalid Input",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                viewModel.updateTodoItemText(todoText = todoText)
                                viewModel.onEvents(TodoListEvents.SaveTodoItem)
                                todoText = ""
                                isTextFieldEmpty = false
                            }
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Button"
                        )
                    }
                }
                HorizontalDivider()
                LazyColumnScrollbar(
                    state = listStateForActiveTodos,
                    settings = ScrollbarSettings(
                        thumbSelectedColor = MaterialTheme.colorScheme.tertiaryContainer,
                        thumbUnselectedColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .padding(16.dp),
                        state = listStateForActiveTodos
                    ) {
                        items(anyTodoItemActive) { todoItem ->
                            TodoItem(
                                todoItem = todoItem,
                                onCheckPress = {
                                    viewModel.updateTodoItemStatus(todoItem)
                                },
                                onDeleteClick = {
                                    viewModel.onEvents(TodoListEvents.DeleteTodoItem(todoItem))
                                }
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
                if(anyTodoItemDone.isNotEmpty()) {
                    Row(
                        modifier = modifier.padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Done",
                            modifier = Modifier.padding(4.dp)
                        )
                        HorizontalDivider()
                    }
                    LazyColumnScrollbar(
                        state = listStateForDoneTodos,
                        settings = ScrollbarSettings(
                            thumbSelectedColor = MaterialTheme.colorScheme.tertiaryContainer,
                            thumbUnselectedColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .padding(16.dp),
                            state = listStateForDoneTodos
                        ) {
                            items(anyTodoItemDone) {
                                TodoItem(
                                    todoItem = it,
                                    onCheckPress = {
                                        viewModel.updateTodoItemStatus(it)
                                    },
                                    onDeleteClick = {
                                        viewModel.onEvents(TodoListEvents.DeleteTodoItem(it))
                                    }
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun Prev() {
    TodoListScreen()
}