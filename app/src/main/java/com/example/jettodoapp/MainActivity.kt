package com.example.jettodoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettodoapp.components.EditDialog
import com.example.jettodoapp.components.TaskList
import com.example.jettodoapp.ui.theme.JetTodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    viewModel: MainViewModel = hiltViewModel(),
) {
    if (viewModel.isShowDialog) {
        EditDialog()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.isShowDialog = true },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "タスクの新規作成",
                )
            }
        },
    ) {
        Log.d("", it.toString())

        var tasks = viewModel.tasks.collectAsState(initial = emptyList()).value

        Box(
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            TaskList(
                tasks = tasks,
                onTapRow = {
                    viewModel.setEditingTask(it)
                    viewModel.isShowDialog = true
                },
                onDeleteTap = {
                    viewModel.deleteTask(it)
                },
            )
        }


    }
}

