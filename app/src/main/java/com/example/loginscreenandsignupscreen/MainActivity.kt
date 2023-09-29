package com.example.loginscreenandsignupscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.loginscreenandsignupscreen.ui.theme.LoginScreenandSignupScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreenandSignupScreenTheme {
                Application()



            }

        }
    }
}








