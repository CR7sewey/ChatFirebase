package com.example.chatfirebase.presentation.chat

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.chatfirebase.common.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.util.UUID

class ChatViewModel(private val firebase: FirebaseDatabase, private val context: Context): ViewModel() {

    private val _uiMessages = MutableStateFlow<List<Message>>(emptyList())
    val uiMessages: StateFlow< List<Message>> = _uiMessages
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
    var username: String = ""
    var password: String = ""
    var userId: String = ""

    var messageText by mutableStateOf("")

    init {
        val messageRef = firebase.reference.child("messages")

        messageRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val messagesList = mutableListOf<Message>()
                snapshot.children.forEach{ child ->
                    val message = child.getValue(Message::class.java) ?: Message()
                    message.let { it -> messagesList.add(it) }
                }
                _uiMessages.value = messagesList
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun onMessageTextChanged(text: String) {
        messageText = text
    }


    fun sendMessage() {
        viewModelScope.launch {
            username = sharedPreferences.getString("USERNAME", "") ?: ""
            password = sharedPreferences.getString("PASSWORD", "") ?: ""
            userId = sharedPreferences.getString("USERID", "") ?: ""
        }

        if (messageText =="") {
            return
        }

        val newMessage = Message(id = UUID.randomUUID().toString(),messageText,userId, System.currentTimeMillis() / 1000L)

        firebase.reference.child("messages").push().setValue(newMessage)
            .addOnSuccessListener {
                //Depois que a message foi salva a gente pode tratar
            }
            .addOnFailureListener {
                // Tratamento de Falhas
            }

        messageText = ""


    }

}