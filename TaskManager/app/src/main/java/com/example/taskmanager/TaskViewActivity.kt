package com.example.taskmanager

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onAddTaskClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTaskClick) {
                Icon(Icons.Filled.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(tasks) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = task.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Priorité : ${task.priority}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        if(task.priority == "Urgent") {
                            Circle(Color.Red)
                        }else if(task.priority == "Normal"){
                            Circle(Color.Yellow)
                        }
                        else{
                            Circle(Color.Black)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(onTaskAdded: (String, String, String) -> Unit) {
    var taskText by remember { mutableStateOf("") }
    var taskDescriptionText by remember { mutableStateOf("") }
    var taskPriorityText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val priorityOptions = listOf("Urgent", "Normal", "Non important")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = taskText,
            onValueChange = { taskText = it },
            label = { Text("Nouvelle tâche") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = taskDescriptionText,
            onValueChange = { taskDescriptionText = it },
            label = { Text("Description...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = taskPriorityText,
                onValueChange = {},
                readOnly = true,
                label = { Text("Priorité") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                priorityOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            taskPriorityText = option
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (taskText.isNotBlank() && taskPriorityText.isNotBlank()) {
                    onTaskAdded(taskText, taskDescriptionText, taskPriorityText)
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Ajouter")
        }
    }
}

@Composable
fun Circle(color : Color){
    Canvas(modifier = Modifier.size(100.dp), onDraw = {
        drawCircle(color = color)
    })
}
