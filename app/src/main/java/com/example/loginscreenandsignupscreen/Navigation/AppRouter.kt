package com.example.loginscreenandsignupscreen.Navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    object SignupScreen : Screen()

    object LoginScreen : Screen()
    object TermsAndConditionsScreens : Screen()
    object HomeScreen:Screen()

}
    object AppRouter {

        val currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignupScreen)
        fun navigateTo(destination: Screen) {
            currentScreen.value = destination
        }
    }
