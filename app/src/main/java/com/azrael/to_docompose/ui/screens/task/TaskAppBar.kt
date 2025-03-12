package com.azrael.to_docompose.ui.screens.task

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.azrael.to_docompose.R
import com.azrael.to_docompose.data.model.Priority
import com.azrael.to_docompose.data.model.ToDoTask
import com.azrael.to_docompose.ui.theme.topAppBarBackgroundColor
import com.azrael.to_docompose.ui.theme.topAppBarContentColor
import com.azrael.to_docompose.util.Action

@Composable
fun TaskAppBar(selectedTask: ToDoTask?, navigateToListScreen: (Action) -> Unit) {
    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreen)
    } else {
        EditTaskAppBar(selectedTask, navigateToListScreen)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        navigationIcon = { BackAction(navigateToListScreen) },
        title = {
            Text(
                text = stringResource(R.string.add_task_title),
                color = MaterialTheme.colorScheme.topAppBarContentColor
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor),
        actions = { AddAction(navigateToListScreen) }
    )
}

@Composable
fun BackAction(onBackClicked: (Action) -> Unit) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_arrow_title),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun AddAction(onAddClicked: (Action) -> Unit) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.add_task_title),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskAppBar(selectedTask: ToDoTask, navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        navigationIcon = { CloseAction(navigateToListScreen) },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colorScheme.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor),
        actions = {
            DeleteAction(navigateToListScreen)
            UpdateAction(navigateToListScreen)
        }
    )
}

@Composable
fun CloseAction(onCloseClicked: (Action) -> Unit) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(R.string.close_icon_title),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(onDeleteClicked: (Action) -> Unit) {
    IconButton(onClick = { onDeleteClicked(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete_icon_title),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(onUpdateClicked: (Action) -> Unit) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.update_icon_title),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
@Preview
fun NewTaskAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = {})
}

@Composable
@Preview
fun EditTaskAppBarPreview() {
    EditTaskAppBar(
        selectedTask = ToDoTask(
            0,
            "Edit Task",
            "Description",
            Priority.LOW
        ), navigateToListScreen = {})
}