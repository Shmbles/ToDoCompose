package com.azrael.to_docompose.ui.screens.task

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.azrael.to_docompose.R
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

    val context = LocalContext.current

    Scaffold(topBar = {
        TaskAppBar(
            selectedTask,
            navigateToListScreen = { action ->
                if (action == Action.NO_ACTION) {
                    navigateToListScreen(action)
                } else {
                    if (sharedViewModel.validateFields()) {
                        navigateToListScreen(action)
                    } else {
                        displayToastEmptyFields(context = context)
                    }
                }
            })
    },
        content = {
            TaskContent(
                title = title,
                onTitleChange = { sharedViewModel.updateTitle(it) },
                description = description,
                onDescriptionChange = { sharedViewModel.description.value = it },
                priority = priority,
                onPriorityChange = { sharedViewModel.priority.value = it })
        })
}

fun displayToastEmptyFields(context: Context) {
    Toast.makeText(context, context.getString(R.string.fields_empty), Toast.LENGTH_SHORT).show()
}