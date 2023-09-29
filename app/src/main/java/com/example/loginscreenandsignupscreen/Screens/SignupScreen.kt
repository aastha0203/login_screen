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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.loginscreenandsignupscreen.AppComponent.ButtonComponent
import com.example.loginscreenandsignupscreen.AppComponent.CheckboxComponent
import com.example.loginscreenandsignupscreen.AppComponent.ClickableLoginTextComponent
import com.example.loginscreenandsignupscreen.AppComponent.DividerTextComponent
import com.example.loginscreenandsignupscreen.AppComponent.HeadingTextComponent
import com.example.loginscreenandsignupscreen.AppComponent.MyTextField
import com.example.loginscreenandsignupscreen.AppComponent.NormalTextComponent
import com.example.loginscreenandsignupscreen.AppComponent.PasswordTextField
import com.example.loginscreenandsignupscreen.Data.SignupViewModel
import com.example.loginscreenandsignupscreen.Data.SignupUIEvent

import com.example.loginscreenandsignupscreen.Navigation.AppRouter
import com.example.loginscreenandsignupscreen.Navigation.Screen
import com.example.loginscreenandsignupscreen.R


@Composable
fun SignupScreen(loginViewModel : SignupViewModel= viewModel() ) {
    Box(modifier = Modifier.fillMaxSize(),) {
        Surface(
            color= Color.White,
            modifier= Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)

        )
        {
            Column(modifier=Modifier.fillMaxSize()) {
                NormalTextComponent(value = stringResource(id = R.string.hello))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier=Modifier.height(20.dp))
                MyTextField(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource(id = R.drawable.baseline_person_24),
                    onTextChanged = {
                        loginViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))

                    },
                    errorStatus = loginViewModel.registrationUIState.value.firstNameError)
                MyTextField(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource(id = R.drawable.baseline_person_24), onTextChanged = {
                        loginViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
                    errorStatus = loginViewModel.registrationUIState.value.lastNameError)
                MyTextField(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.baseline_email_24),
                    onTextChanged = {loginViewModel.onEvent(SignupUIEvent.EmailChanged(it))},
                    errorStatus = loginViewModel.registrationUIState.value.emailError)

                PasswordTextField(
                    labelValue = stringResource(id = R.string.password), painterResource(id =R.drawable.baseline_lock_24 ), onTextChanged = {
                        loginViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.registrationUIState.value.passwordError )


                CheckboxComponent(value = stringResource(id = R.string.terms_conditions),
                    onTextSelected = {
                        AppRouter.navigateTo(Screen.TermsAndConditionsScreens)

                    }
                    ,onCheckedChange={
                        loginViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }

                )
                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(value = stringResource(id=R.string.register),
                    onButtonClicked = {
                        loginViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationPassed.value


                )




                Spacer(modifier = Modifier.height(30.dp))

                DividerTextComponent()
                Spacer(modifier = Modifier.height(30.dp))
                ClickableLoginTextComponent(tryingToLogin = true,onTextSelected ={
                    AppRouter.navigateTo(Screen.LoginScreen)


                })





            }

        }
        if (loginViewModel.signUpInProgress.value){
        CircularProgressIndicator()
        }

    }




}
@Preview
@Composable
fun DefaultPreviewOfSignupScreen(){
    SignupScreen()

}