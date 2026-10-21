package xyz.hyderhadi.todolist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import xyz.hyderhadi.todolist.domain.model.TodoItem

@Composable
fun TodoItem(
    modifier: Modifier = Modifier,
    todoItem: TodoItem,
    onCheckPress: () -> Unit,
    onDeleteClick:() -> Unit
) {

    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = todoItem.isDone,
                    onCheckedChange = {
                        onCheckPress()
                    },
                )

                Text(
                    text = todoItem.todoText,
                    textDecoration = if(todoItem.isDone) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

            IconButton(
                onClick = {
                    onDeleteClick()
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                )
            }
        }
    }
}




@Preview()
@Composable
fun Prev2() {
    TodoItem(
        todoItem = TodoItem(
            todoText = "Go get somkfkdsfkdskfksdfkdskfkdsfe Grokdsajdjsadjsajdajsdjsadjsceries",
            isDone = false
        ),
        onCheckPress = { },
        onDeleteClick = { }
    )
}