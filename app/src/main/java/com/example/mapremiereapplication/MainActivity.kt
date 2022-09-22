package com.example.mapremiereapplication

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mapremiereapplication.ui.theme.MapremiereapplicationTheme
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapremiereapplicationTheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                // A surface container using the 'background' color from the theme
                    Home(windowSizeClass)
            }
        }
    }
}

@Composable
fun Home(classes: WindowSizeClass) {
    val classeLargeur = classes.widthSizeClass
    when (classeLargeur) {
        WindowWidthSizeClass.Compact-> PageAccueilColonne()
        WindowWidthSizeClass.Medium -> PageAccueilLigne()
        WindowWidthSizeClass.Expanded -> PageAccueilLigne()
    }

}

@Composable
fun PageAccueilColonne(){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.size(30.dp))
        ImageProfil()
        Text("Bastien Hatinguais", fontSize = 30.sp)
        Text("Elève en 2ème année de cycle ingénieur en alternance", fontSize = 15.sp)
        Text("Ecole d'ingénieur ISIS - INU Champollion", fontSize = 15.sp, fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.size(50.dp))
        Socials()
        Spacer(modifier = Modifier.size(100.dp))
        ButtonFilled("Démarrer")
    }
}

@Composable
fun PageAccueilLigne(){
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.size(30.dp))
        ImageProfil()
        Spacer(modifier = Modifier.size(30.dp))
        Column() {
            Text("Bastien HATINGUAIS", fontSize = 30.sp)
            Text("Elève en 2ème année de cycle ingénieur en alternance", fontSize = 15.sp)
            Text("Ecole d'ingénieur ISIS - INU Champollion", fontSize = 15.sp, fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.size(30.dp))
            Socials()
            Spacer(modifier = Modifier.size(30.dp))
            ButtonFilled("Démarrer")
        }
    }
}

@Composable
fun ImageProfil() {
    Image(painter = painterResource(R.mipmap.image), contentDescription = "profil image", contentScale = ContentScale.Crop, modifier = Modifier.size(200.dp).clip(
        CircleShape))
}

@Composable
fun Socials(){
        Row(modifier = Modifier.padding(end = 38.dp)) {
            Icon(Icons.Rounded.Email, contentDescription = "Localized description", modifier=Modifier.padding(start = 12.dp))
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "bastien.hatinguais@gmail.com")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(R.mipmap.linkedin_foreground),
                contentDescription = "Linkedin Icon",
                modifier = Modifier
                    .requiredSize(50.dp)
                    .padding(0.dp)
            )
            Text(text = "www.linkedin.com/bastien-hatinguais")
        }
}

@Composable
fun ButtonFilled(nom : String) {
    Button(onClick = { /* Do something! */ }) {
        Text(nom)
    }
}
