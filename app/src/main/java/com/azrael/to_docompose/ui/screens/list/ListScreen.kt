package com.azrael.to_docompose.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.azrael.to_docompose.R
import com.azrael.to_docompose.ui.theme.TOP_APP_BAR_HEIGHT
import com.azrael.to_docompose.ui.theme.fabBackgroundColor
import com.azrael.to_docompose.ui.viewmodel.SharedViewModel
import com.azrael.to_docompose.util.SearchAppBarState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(navigateToTaskScreen: (taskId: Int) -> Unit, sharedViewModel: SharedViewModel) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searTextState: String by sharedViewModel.searchTextState

    Scaffold(
        topBar = { ListAppBar(sharedViewModel, searchAppBarState, searTextState) },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    ) {
        Column(
            modifier = Modifier.padding(top = TOP_APP_BAR_HEIGHT)
        ) {
            ListContent(
                tasks = allTasks,
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