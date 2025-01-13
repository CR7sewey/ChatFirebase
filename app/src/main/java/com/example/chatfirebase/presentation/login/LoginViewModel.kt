package com.example.chatfirebase.presentation.login

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val context: Context) : ViewModel() {

    // to save info key:value
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)

    var userID: String = ""
    var username: String = ""
    var password: String = ""

    fun saveCredentials(username: String, password: String) {
        userID = if(username == "userone") {
            "1"
        } else { "2"}
        this.username = username
        this.password = password

        viewModelScope.launch {
            with(sharedPreferences.edit()) {
                putString("USERID", userID)
                putString("USERNAME", username)
                putString("PASSWORD", password)
                apply()
            }
        }

    }


}

class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(context) as T
        }
        throw IllegalArgumentException("Viewmodel Class Desconhecido")
    }
}