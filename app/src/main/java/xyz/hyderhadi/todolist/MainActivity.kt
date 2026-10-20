package xyz.hyderhadi.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import xyz.hyderhadi.todolist.ui.TodoListScreen
import xyz.hyderhadi.todolist.ui.theme.TodoListTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListTheme {
                Surface {
                    TodoListScreen()
                }
            }
        }
    }
}