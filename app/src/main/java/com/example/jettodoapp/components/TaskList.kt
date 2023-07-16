package com.example.jettodoapp.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.jettodoapp.Task


@Composable
fun TaskList(
    tasks: List<Task>,
    onTapRow: (Task) -> Unit,
    onDeleteTap: (Task) -> Unit,
) {
    LazyColumn {
        items(tasks.count()) { index ->
            val task = tasks[index]
            TaskRow(
                task = task,
                onTapRow = onTapRow,
                onDeleteTap = onDeleteTap,
            )
        }
    }
}