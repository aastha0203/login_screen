package com.example.loginscreenandsignupscreen.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginscreenandsignupscreen.AppComponent.ButtonComponent
import com.example.loginscreenandsignupscreen.AppComponent.ClickableLoginTextComponent
import com.example.loginscreenandsignupscreen.AppComponent.DividerTextComponent
import com.example.loginscreenandsignupscreen.AppComponent.HeadingTextComponent
import com.example.loginscreenandsignupscreen.AppComponent.MyTextField
import com.example.loginscreenandsignupscreen.AppComponent.NormalTextComponent
import com.example.loginscreenandsignupscreen.AppComponent.PasswordTextField
import com.example.loginscreenandsignupscreen.AppComponent.UnderLinedTextComponent
import com.example.loginscreenandsignupscreen.Data.LoginUIEvent
import com.example.loginscreenandsignupscreen.Data.LoginViewModel
import com.example.loginscreenandsignupscreen.Data.SignupViewModel
import com.example.loginscreenandsignupscreen.Navigation.AppRouter

import com.example.loginscreenandsignupscreen.Navigation.Screen
import com.example.loginscreenandsignupscreen.Navigation.SystemBackButtonHandler
import com.example.loginscreenandsignupscreen.R


@Composable
fun LoginScreen(loginViewModel: LoginViewModel= viewModel()){
    Box(modifier=Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Surface(
            color= Color.White,
            modifier= Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)

        ){
            Column(modifier = Modifier
                .fillMaxSize()) {


                NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(20.dp))
                MyTextField(labelValue = stringResource(id = R.string.email) ,
                    painterResource (id=R.drawable.baseline_email_24 ), onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )
                PasswordTextField(
                    labelValue = stringResource(id = R.string.password), painterResource(id=R.drawable.baseline_lock_24), onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )
                Spacer(modifier = Modifier.height(40.dp))

                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))
                Spacer(modifier = Modifier.height(40.dp))
                ButtonComponent(value = stringResource(id = R.string.login), onButtonClicked ={
                         loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                },
                    isEnabled =  loginViewModel.allValidationPassed.value)
                Spacer(modifier = Modifier.height(40.dp))
                DividerTextComponent()
                ClickableLoginTextComponent( tryingToLogin = false,onTextSelected = {
                    AppRouter.navigateTo(Screen.SignupScreen)
                })




            }

        }
        if(loginViewModel.loginInProgress.value){
        CircularProgressIndicator()
        }

    }

    SystemBackButtonHandler {
     AppRouter.navigateTo(Screen.SignupScreen)
     }


}

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}