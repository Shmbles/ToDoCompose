package com.azrael.to_docompose.ui.screens.task

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.azrael.to_docompose.R
import com.azrael.to_docompose.data.model.Priority
import com.azrael.to_docompose.data.model.ToDoTask
import com.azrael.to_docompose.ui.viewmodel.SharedViewModel
import com.azrael.to_docompose.util.Action

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val title: String = sharedViewModel.title
    val description: String = sharedViewModel.description
    val priority: Priority = sharedViewModel.priority

    val context = LocalContext.current

    BackHandler { navigateToListScreen(Action.NO_ACTION) }

    Scaffold(topBar = {
        TaskAppBar(
            selectedTask = selectedTask,
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
        content = { padding ->
            TaskContent(
                modifier = Modifier.padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
                title = title,
                onTitleChange = { sharedViewModel.updateTitle(it) },
                description = description,
                onDescriptionChange = { sharedViewModel.updateDescription(it) },
                priority = priority,
                onPriorityChange = { sharedViewModel.updatePriority(it) })
        })
}

fun displayToastEmptyFields(context: Context) {
    Toast.makeText(context, context.getString(R.string.fields_empty), Toast.LENGTH_SHORT).show()
}