package xyz.hyderhadi.todolist.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val todoText: String,
    val isDone: Boolean = false
)