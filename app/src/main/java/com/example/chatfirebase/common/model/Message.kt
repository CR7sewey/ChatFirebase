package com.example.chatfirebase.common.model

data class Message(
    val id: String? = null,
    val text: String? = null,
    val senderId: String? = null,
    val timestamp: Long? = null
)
