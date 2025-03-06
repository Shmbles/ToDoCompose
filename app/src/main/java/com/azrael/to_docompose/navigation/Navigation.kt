package com.azrael.to_docompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.azrael.to_docompose.navigation.destinations.listComposable
import com.azrael.to_docompose.navigation.destinations.taskComposable
import com.azrael.to_docompose.ui.viewmodel.SharedViewModel
import com.azrael.to_docompose.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(navController: NavHostController, sharedviewmodel: SharedViewModel) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable(screen.task, sharedviewmodel)
        taskComposable(screen.list, sharedviewmodel)
    }
}