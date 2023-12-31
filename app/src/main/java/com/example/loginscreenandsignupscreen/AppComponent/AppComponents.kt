package com.example.loginscreenandsignupscreen.AppComponent


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginscreenandsignupscreen.R
import com.example.loginscreenandsignupscreen.ui.theme.BgColor
import com.example.loginscreenandsignupscreen.ui.theme.Primary
import com.example.loginscreenandsignupscreen.ui.theme.Secondary
import com.example.loginscreenandsignupscreen.ui.theme.TextColor
import com.example.loginscreenandsignupscreen.ui.theme.componentShapes


@Composable
fun NormalTextComponent(value:String){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color= TextColor,
        textAlign = TextAlign.Center
    )


}
@Composable
fun HeadingTextComponent(value:String){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color= TextColor,
        textAlign = TextAlign.Center




    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(labelValue: String, painterResource: Painter, onTextChanged: (String) -> Unit, errorStatus:Boolean){
    val textValue=remember{
        mutableStateOf(value = "")
    }
    val localFocusManager= LocalFocusManager.current

    OutlinedTextField(
        modifier= Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),label={Text(text=labelValue)},
        colors= TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            //backgroundColor= BgColor
        ), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value=textValue.value,
        onValueChange ={
            textValue.value= it
            onTextChanged(it)},
        leadingIcon = {
            Icon(painter = painterResource, contentDescription ="" )
        },
        isError = !errorStatus

        )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(labelValue: String, painterResource:Painter, onTextChanged: (String) -> Unit, errorStatus: Boolean){
    val localFocusManager= LocalFocusManager.current
    val password=remember{
        mutableStateOf(value = "")
    }
    val passwordVisible= remember {
        mutableStateOf(false)}

    OutlinedTextField(
        modifier= Modifier
            .fillMaxWidth()
            .clip(componentShapes.small),label={Text(text=labelValue)},
        colors=TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary
        ), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done ),
        singleLine=true,
        maxLines=1,
        keyboardActions= KeyboardActions{
            localFocusManager.clearFocus()

        },


        value=password.value,
        onValueChange ={
            password.value= it
                       onTextChanged(it)},
        leadingIcon = {
            Icon(painter = painterResource, contentDescription ="" )

        },
        trailingIcon = {
            val iconImage=if(passwordVisible.value){
                Icons.Filled.Visibility

            }
            else{
                Icons.Filled.VisibilityOff
            }
            var description=if(passwordVisible.value){
                stringResource(id = R.string.hide_password)
            }
            else{
                stringResource(id = R.string.show_password)
            }
            IconButton(onClick ={passwordVisible.value=!passwordVisible.value}) {
                Icon(imageVector = iconImage, contentDescription =description )
            }

        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else
            PasswordVisualTransformation(),
        isError = !errorStatus


    )

}
@Composable
fun CheckboxComponent(value: String,onTextSelected: (String) -> Unit,onCheckedChange:(Boolean)->Unit){
    Row(modifier= Modifier
        .fillMaxWidth()
        .heightIn(56.dp),

        verticalAlignment = Alignment.CenterVertically,
    ){
        val checkedState= remember {
            mutableStateOf(false)
        }
        Checkbox(checked =checkedState.value , onCheckedChange ={
            checkedState.value= !checkedState.value
            onCheckedChange.invoke(it)

        } )
        ClickableTextComponent(value=value,onTextSelected)
    }
}
@Composable
fun ClickableTextComponent(value: String,onTextSelected:(String) ->Unit){
    val initialText="By continuing you accept our "
    val privacyPolicyText="Privacy Policy "
    val andText=" and "
    val termsAndConditionsText="Terms Of Use"
    val annotatedString= buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag=privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag=termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }


    }
    ClickableText(text = annotatedString, onClick = {offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span->
                Log.d("ClickableTextComponent","{$span}")
                if((span.item == termsAndConditionsText) || (span.item==privacyPolicyText)){
                    onTextSelected(span.item)
                }
            }


    })
}
@Composable
  fun ButtonComponent(value:String,onButtonClicked :() ->Unit,isEnabled:Boolean=false){
    Button(onClick = { onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        enabled = isEnabled

    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = RoundedCornerShape(10.dp)

            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
        }
return@Button

    }
}


@Composable
fun DividerTextComponent(){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color=Color.Gray,
            thickness = 1.dp)
        Text(text = stringResource(id = R.string.or),
            modifier = Modifier.padding(8.dp),
            fontSize = 18.sp,
            color= TextColor)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color=Color.Gray,
            thickness = 1.dp)


    }
}
@Composable
fun ClickableLoginTextComponent(tryingToLogin:Boolean=true,onTextSelected:(String) ->Unit){
    val initialText=if(tryingToLogin) "Already have an Account? " else "Don't have an account yet? "
    val loginText=if (tryingToLogin)"Login " else "Register"

    val annotatedString= buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag=loginText, annotation = loginText)
            append(loginText)
        }



    }
    ClickableText(modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center),text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    Log.d("ClickableTextComponent", "{$span}")
                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }
        })}
@Composable
fun UnderLinedTextComponent(value:String){
    Text(
        text=value,
        modifier=Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color= Color.Gray,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline



    )
}







