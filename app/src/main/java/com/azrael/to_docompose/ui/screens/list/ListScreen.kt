package com.azrael.to_docompose.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.azrael.to_docompose.R
import com.azrael.to_docompose.ui.theme.TOP_APP_BAR_HEIGHT
import com.azrael.to_docompose.ui.theme.fabBackgroundColor
import com.azrael.to_docompose.ui.viewmodel.SharedViewModel
import com.azrael.to_docompose.util.Action
import com.azrael.to_docompose.util.SearchAppBarState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(navigateToTaskScreen: (taskId: Int) -> Unit, sharedViewModel: SharedViewModel) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searTextState: String by sharedViewModel.searchTextState

    val snackbarHostState = rememberBottomSheetScaffoldState().snackbarHostState

    sharedViewModel.handleDatabaseActions(action)
    DisplaySnackBar(
        snackbarHostState,
        { sharedViewModel.handleDatabaseActions(action) },
        { sharedViewModel.action.value = it },
        sharedViewModel.title.value,
        action
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { ListAppBar(sharedViewModel, searchAppBarState, searTextState) },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    ) {
        Column(
            modifier = Modifier.padding(top = TOP_APP_BAR_HEIGHT)
        ) {
            ListContent(
                allTasks = allTasks,
                searchedTasks,
                searchAppBarState,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@Composable
fun ListFab(onFabClicked: (taskId: Int) -> Unit) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        containerColor = MaterialTheme.colorScheme.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_button)
        )
    }
}

@Composable
fun DisplaySnackBar(
    snackbarHostState: SnackbarHostState,
    handleDatabaseActions: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {
    handleDatabaseActions()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = snackbarHostState.showSnackbar(
                    message = setMessage(action, taskTitle),
                    actionLabel = setActionLabel(action)
                )
                undoDeletedTask(action, snackBarResult, onUndoClicked)
            }
        }
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All Tasks Removed."
        else -> "${action.name}: $taskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}