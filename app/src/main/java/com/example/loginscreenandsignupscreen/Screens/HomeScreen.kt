package com.example.loginscreenandsignupscreen.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginscreenandsignupscreen.AppComponent.ButtonComponent
import com.example.loginscreenandsignupscreen.AppComponent.HeadingTextComponent
import com.example.loginscreenandsignupscreen.Data.LoginViewModel
import com.example.loginscreenandsignupscreen.Data.SignupViewModel

@Composable
fun HomeScreen(signupViewModel: SignupViewModel= viewModel()){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(modifier=Modifier.fillMaxSize()){
            HeadingTextComponent(value = "Home")
            ButtonComponent(value ="Logout" , onButtonClicked = {
            signupViewModel.logout()},
                isEnabled = true)
        }

    }

}
