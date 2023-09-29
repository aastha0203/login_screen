package com.example.loginscreenandsignupscreen


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.loginscreenandsignupscreen.Navigation.AppRouter

import com.example.loginscreenandsignupscreen.Navigation.Screen
import com.example.loginscreenandsignupscreen.Screens.HomeScreen
import com.example.loginscreenandsignupscreen.Screens.LoginScreen
import com.example.loginscreenandsignupscreen.Screens.SignupScreen
import com.example.loginscreenandsignupscreen.Screens.TermsAndConditionsScreens


@Composable
fun Application(){
    Surface(modifier = Modifier.fillMaxSize(),
        color= Color.White) {
        Crossfade(targetState = AppRouter.currentScreen) { currentState->
            when(currentState.value){
                is Screen.TermsAndConditionsScreens->{
                    TermsAndConditionsScreens()
                }

                is Screen.SignupScreen ->{
                    SignupScreen()}

                is Screen.LoginScreen->{
                    LoginScreen()
                }
                is Screen.HomeScreen->{
                    HomeScreen()
                }
            }

        }

    }

}
