package com.example.loginscreenandsignupscreen.Data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.loginscreenandsignupscreen.Navigation.AppRouter
import com.example.loginscreenandsignupscreen.Navigation.Screen
import com.example.loginscreenandsignupscreen.rules.Validator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class SignupViewModel : ViewModel() {
    private val TAG = SignupViewModel::class.simpleName
    var registrationUIState = mutableStateOf(RegistrationUIState())
    var allValidationPassed= mutableStateOf(false)
    var signUpInProgress= mutableStateOf(false)
    fun onEvent(event: SignupUIEvent) {
       // validateDataWithRules()
        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                //validateDataWithRules()
                printState()
            }

            is SignupUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )

                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }

            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signup()
            }
            is SignupUIEvent.PrivacyPolicyCheckBoxClicked->{
                registrationUIState.value=registrationUIState.value.copy(
                privacyPolicyAccepted = event.status
                )

            }
        }
        validateDataWithRules()
    }

    private fun signup(){
        Log.d(TAG,"Inside_Signup")
        printState()
       // validateDataWithRules()
        createUserInFirebase(
            email = registrationUIState.value.email,
            password =registrationUIState.value.password
        )

    }
    private fun validateDataWithRules(){
       val fNameResult= Validator.validateFirstName(
           fname = registrationUIState.value.firstName
       )
        val lNameResult=Validator.validateLastName(
            lname = registrationUIState.value.lastName
        )
        val emailResult=Validator.validateEmail(
            email = registrationUIState.value.email
        )
        val passwordResult=Validator.validatePassword(
            password = registrationUIState.value.password
        )
        val privacyPolicyResult=Validator.validatePrivacyPolicyAcceptance(
         statusValue = registrationUIState.value.privacyPolicyAccepted

        )
        Log.d(TAG,"Inside_validationDataWithRules")
        Log.d(TAG,"fnameResut=$fNameResult")
        Log.d(TAG,"lNameResult= $lNameResult")
        Log.d(TAG,"emailResult=$emailResult")
        Log.d(TAG,"passwordResult=$passwordResult")
        Log.d(TAG,"privacyPolicyResult=$privacyPolicyResult")
        registrationUIState.value=registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )
       allValidationPassed.value=fNameResult.status&& lNameResult.status && emailResult.status && passwordResult.status && privacyPolicyResult.status
    }
    private fun printState(){
        Log.d(TAG,"Inside_printSate")
        Log.d(TAG,registrationUIState.value.toString())
    }
private fun createUserInFirebase(email:String,password:String){
    signUpInProgress.value=true
FirebaseAuth.getInstance()
    .createUserWithEmailAndPassword(email, password)
    .addOnCompleteListener {
         Log.d(TAG,"Inside_OnCompleteListener")
        Log.d(TAG,"isSuccessful=${it.isSuccessful}")
        signUpInProgress.value=false
        if (it.isSuccessful){
            AppRouter.navigateTo(Screen.HomeScreen)
    }
    }
    .addOnFailureListener{
        Log.d(TAG,"Inside_OnFailureListener")
        Log.d(TAG,"Exception=${it.message}")
        Log.d(TAG,"Exception=${it.localizedMessage}")


    }
}
    fun logout(){
      val firebaseAuth=FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener=AuthStateListener{
            if(it.currentUser==null){
                Log.d(TAG,"Inside signout success")
                AppRouter.navigateTo(Screen.LoginScreen)
            }
            else{
                Log.d(TAG,"Inside signout is not complete")
            }
        }
        firebaseAuth.addAuthStateListener (authStateListener)
    }



}