package com.example.loginscreenandsignupscreen

import android.app.Application
import com.google.firebase.FirebaseApp

class loginscreenandsignupscreenApp:Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)}
}