package com.example.jettodoapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jettodoapp.Task

@Composable
fun TaskRow(
    task: Task,
    onTapRow: (Task) -> Unit,
    onDeleteTap: (Task) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .fillMaxWidth(),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
        ),
    )
    {
        Row(
            modifier = Modifier
                .clickable { onTapRow(task) }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = task.title)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { onDeleteTap(task) },
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "タスクの削除",
                )
            }
        }


    }
}

@Preview
@Composable
fun PreviewTaskRow() {
    TaskRow(
        task = Task(title = "プレビュー", description = "詳細"),
        onTapRow = {},
        onDeleteTap = {},
    )
}