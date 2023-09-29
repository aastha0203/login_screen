package com.example.loginscreenandsignupscreen.Screens



import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.loginscreenandsignupscreen.R


@Composable
fun ProfileScreen() {
    val notification= rememberSaveable{mutableStateOf("")}
    if (notification.value.isNotEmpty()){
        Toast.makeText(LocalContext.current,notification.value,Toast.LENGTH_LONG).show()
        notification.value=""
    }

    Column (
        modifier= Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp)) {
        Row(modifier= Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White)
            , horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "Cancel",modifier=Modifier.clickable { notification.value="No changes Done" })
            Text(text = "Save",modifier=Modifier.clickable { notification.value="Changes Saved" })

    }
        ProfileImage()

    }

}
@Composable
fun ProfileImage() {
    val imageUri = rememberSaveable { mutableStateOf("") }
    val painter= rememberAsyncImagePainter(
        if (imageUri.value.isEmpty()){
            R.drawable.baseline_email_24
        }
        else{
            imageUri.value
        }
    )
    val launcher= rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
        uri -> Uri? ->
        uri?.let { imageUri.value=it.toString() }
    }


    Column (
        modifier= Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Card(shape= CircleShape,
           modifier = Modifier
               .padding(8.dp)
               .size(100.dp)) {
            Image(painter = painter, contentDescription = null,modifier= Modifier
                .wrapContentSize()
                .clickable { launcher.launch("image/")},
                contentScale = ContentScale.Crop)

        }
        Text(text = "Change profile picture")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOfProfileScreen(){
    ProfileScreen()
}






