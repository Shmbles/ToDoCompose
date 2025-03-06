package com.azrael.to_docompose.ui.screens.task

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.azrael.to_docompose.data.model.Priority
import com.azrael.to_docompose.data.model.ToDoTask
import com.azrael.to_docompose.ui.viewmodel.SharedViewModel
import com.azrael.to_docompose.util.Action

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    Scaffold(topBar = { TaskAppBar(selectedTask, navigateToListScreen) },
        content = {
            TaskContent(
                title = title,
                onTitleChange = { sharedViewModel.title.value = it },
                description = description,
                onDescriptionChange = { sharedViewModel.description.value = it },
                priority = priority,
                onPriorityChange = { sharedViewModel.priority.value = it })
        })
}