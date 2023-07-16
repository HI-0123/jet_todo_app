package com.example.jettodoapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettodoapp.MainViewModel

@Composable
fun EditDialog(
    viewModel: MainViewModel = hiltViewModel(),
) {
    DisposableEffect(Unit){
        onDispose {
            viewModel.reset()
        }
    }

    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = { viewModel.isShowDialog = false },
        title = {
            Text(
                text = if (!viewModel.isEditing) "タスク新規作成" else "タスク編集"
            )
        },
        text = {
            Column {
                buildTextField(
                    title = "タイトル",
                    value = viewModel.title,
                    onValueChange = { viewModel.title = it },
                )
                Spacer(modifier = Modifier.height(8.dp))
                buildTextField(
                    title = "詳細",
                    value = viewModel.description,
                    onValueChange = { viewModel.description = it },
                )
            }

        },
        confirmButton = {
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.weight(1f))
                buildButton(
                    title = "キャンセル",
                    onClick = { viewModel.isShowDialog = false },
                )
                Spacer(modifier = Modifier.width(8.dp))
                buildButton(
                    title = "OK",
                    onClick = {
                        viewModel.isShowDialog = false
                        if (!viewModel.isEditing)
                            viewModel.createTask() else viewModel.updateTask()
                    },
                )

            }
        },
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun buildTextField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column {
        Text(text = title)
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
        )
    }
}

@Composable
private fun buildButton(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
    ) {
        Text(title)
    }
}