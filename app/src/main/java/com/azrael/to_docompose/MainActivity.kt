package com.azrael.to_docompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.azrael.to_docompose.navigation.SetupNavigation
import com.azrael.to_docompose.ui.theme.ToDoComposeTheme
import com.azrael.to_docompose.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ToDoComposeTheme {
                navController = rememberNavController()
                SetupNavigation(navController = navController, sharedViewModel = sharedViewModel)
            }
        }
    }
}