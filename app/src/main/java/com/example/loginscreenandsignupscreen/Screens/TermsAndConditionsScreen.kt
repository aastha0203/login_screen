package com.example.loginscreenandsignupscreen.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginscreenandsignupscreen.AppComponent.HeadingTextComponent
import com.example.loginscreenandsignupscreen.Navigation.AppRouter

import com.example.loginscreenandsignupscreen.Navigation.Screen
import com.example.loginscreenandsignupscreen.Navigation.SystemBackButtonHandler
import com.example.loginscreenandsignupscreen.R


@Composable
fun TermsAndConditionsScreens(){
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)){
        HeadingTextComponent(value = stringResource(id = R.string.terms_header))

    }
    SystemBackButtonHandler{
        AppRouter.navigateTo(Screen.SignupScreen)
    }

}
@Preview
@Composable
fun TermsAndConditionsScreensPreview(){
    TermsAndConditionsScreens()
}