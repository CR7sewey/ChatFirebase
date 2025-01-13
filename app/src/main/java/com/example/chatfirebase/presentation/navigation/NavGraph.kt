package com.example.chatfirebase.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatfirebase.presentation.chat.ChatScreen
import com.example.chatfirebase.presentation.login.LoginViewModel
import com.example.chatfirebase.presentation.login.LoginViewModelFactory
import com.example.chatfirebase.presentation.login.ui.LoginScreen
import com.google.firebase.FirebaseApp

sealed class Screen(val route: String) {
    object Login: Screen("login")

    object Chat: Screen("chat")
}

@Composable
fun NavGraph(startDestination: String = Screen.Login.route) {
    val navController = rememberNavController()

    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(LocalContext.current))
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.Login.route) {
            // Login Screen
            LoginScreen(navController,loginViewModel)
        }

        composable(route = Screen.Chat.route) {
            // Chat Screen
            ChatScreen()
        }
    }
}