package com.example.chatfirebase.presentation.login.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.chatfirebase.components.LoginUsername
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.chatfirebase.components.LoginPassword
import com.example.chatfirebase.presentation.login.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController, loginViewModel: LoginViewModel) {
    LoginContent(navHostController, loginViewModel)
}

@Composable
private fun LoginContent(navHostController: NavHostController, loginViewModel: LoginViewModel ,modifier: Modifier= Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    //var text = ""
    Column (modifier = modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
        Text(
            text = "Login",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 50.dp)
        )
        LoginUsername(placeholder = "Username", username, modifier = Modifier, onValueChange = { it: String -> username = it } )
        LoginPassword(placeholder = "Password", password, modifier = Modifier, onValueChange = { it: String -> password = it } )
        Spacer(modifier = modifier.padding(top = 26.dp))
        Button(onClick = {
            if (username.isEmpty() || password.isEmpty()) {
                return@Button
            }
            loginViewModel.saveCredentials(username,password)
            navHostController.navigate(route = "chat")
                         },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Login")
        }
        Button(onClick = {
            username = ""
            password = ""
        },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Clear Credentials")
        }

    }
}