package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TaskApp()
                }
            }
        }
    }
}

@Composable
fun TaskApp() {
    val navController = rememberNavController()
    val tasks = remember { mutableStateListOf<Task>() }

    NavHost(navController = navController, startDestination = "task_list") {
        composable("task_list") {
            TaskListScreen(
                tasks = tasks,
                onAddTaskClick = { navController.navigate("add_task") }
            )
        }
        composable("add_task") {
            AddTaskScreen(
                onTaskAdded = { title, description, priority ->
                    tasks.add(Task(title, description, priority))
                    navController.popBackStack()
                }
            )
        }
    }
}
