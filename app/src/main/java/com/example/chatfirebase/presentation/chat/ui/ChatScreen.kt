package com.example.chatfirebase.presentation.chat.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.chatfirebase.common.model.Message
import com.example.chatfirebase.presentation.chat.ChatViewModel
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navHostController: NavHostController, chatViewModel: ChatViewModel) {
    val chatMessages by chatViewModel.uiMessages.collectAsState()
    ChatScreenContent(chatMessages, chatViewModel)
}

@Composable
fun ChatScreenContent(chatMessages: List<Message>, chatViewModel: ChatViewModel, modifier: Modifier = Modifier) {

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn( modifier = Modifier.weight(1f),
            reverseLayout = false,
            contentPadding = PaddingValues(8.dp)) {


            items(chatMessages) { current ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if(current.senderId == "1") {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Box(
                        modifier = modifier.align(Alignment.CenterVertically).padding(4.dp).background(
                            if (current.senderId == "1") {
                                Color.Green
                            } else {
                                Color.Blue
                            }
                        ).padding(8.dp)
                    ) {
                        current.text?.let {
                            Text(
                                text = current.text,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 24.sp
                            )
                        }
                    }
                    if(current.senderId == "1") {
                        Spacer(modifier = Modifier.weight(1f))
                    }


                }
            }


        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = chatViewModel.messageText,
                onValueChange = { it -> chatViewModel.onMessageTextChanged(it) },
                modifier = Modifier.weight(1f)
            )
            Button( onClick = { chatViewModel.sendMessage()}) {
                Text("Enviar")
            }
        }
    }
}