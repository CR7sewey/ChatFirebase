package com.example.autentication.login

sealed class Screen(val route: String) {
    object Chat: Screen("chat")
}