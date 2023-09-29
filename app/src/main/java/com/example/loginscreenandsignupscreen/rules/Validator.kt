package com.example.loginscreenandsignupscreen.rules

object Validator {
    fun validateFirstName(fname:String):ValidationResult{
        return ValidationResult(
            (!fname.isNullOrEmpty() && fname.length>=3 )
        )


    }
    fun validateLastName(lname:String):ValidationResult{
        return ValidationResult(
            (!lname.isNullOrEmpty() && lname.length>=3 )
        )

    }
    fun validateEmail(email:String):ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty())
        )

    }
    fun validatePassword(password:String):ValidationResult{
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length>=6))
    }
    fun validatePrivacyPolicyAcceptance(statusValue: Boolean):ValidationResult{
        return ValidationResult(
            statusValue

        )
    }
}



data class ValidationResult(
    val status :Boolean=false
)