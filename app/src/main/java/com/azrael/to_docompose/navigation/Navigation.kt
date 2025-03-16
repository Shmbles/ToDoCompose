package com.azrael.to_docompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.azrael.to_docompose.navigation.destinations.listComposable
import com.azrael.to_docompose.navigation.destinations.taskComposable
import com.azrael.to_docompose.ui.viewmodel.SharedViewModel

@Composable
fun SetupNavigation(navController: NavHostController, sharedViewModel: SharedViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.List()
    ) {
        listComposable(
            navigateToTaskScreen = { taskId ->
                navController.navigate(Screen.Task(id = taskId))
            },
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = { action ->
                navController.navigate(Screen.List(action = action)) {
                    popUpTo(Screen.List()) { inclusive = true }
                }
            },
            sharedViewModel = sharedViewModel
        )
    }
}