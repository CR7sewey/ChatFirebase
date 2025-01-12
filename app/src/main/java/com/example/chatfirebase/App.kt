package com.example.chatfirebase

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize

// primeira classe a ser instanciada em no app
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // instanciar firebase
        Firebase.initialize(this)

    }
}