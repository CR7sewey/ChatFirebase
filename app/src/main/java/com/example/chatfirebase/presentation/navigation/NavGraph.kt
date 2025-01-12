package com.example.chatfirebase.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatfirebase.presentation.login.ui.LoginScreen

sealed class Screen(val route: String) {
    object Login: Screen("login")

    object Chat: Screen("chat")
}

@Composable
fun NavGraph(startDestination: String = Screen.Login.route) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.Login.route) {
            // Login Screen
            LoginScreen(navController)
        }

        composable(route = Screen.Chat.route) {
            // Chat Screen
        }
    }
}