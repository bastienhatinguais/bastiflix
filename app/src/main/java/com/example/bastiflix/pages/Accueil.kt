package com.example.bastiflix.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Accueil(navController: NavHostController, classes: WindowSizeClass) {
    val classeLargeur = classes.widthSizeClass
    when (classeLargeur) {
        WindowWidthSizeClass.Compact-> PageAccueilColonne(navController)
        WindowWidthSizeClass.Medium -> PageAccueilLigne(navController)
        WindowWidthSizeClass.Expanded -> PageAccueilLigne(navController)
    }

}

@Composable
fun PageAccueilColonne(navController: NavHostController){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.size(30.dp))
        ImageProfil()
        Text("Bastien Hatinguais", fontSize = 30.sp)
        Text("Elève en 2ème année de cycle ingénieur en alternance", fontSize = 15.sp)
        Text("Ecole d'ingénieur ISIS - INU Champollion", fontSize = 15.sp, fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.size(50.dp))
        Socials()
        Spacer(modifier = Modifier.size(100.dp))
        ButtonFilled("Démarrer", navController)
    }
}

@Composable
fun PageAccueilLigne(navController: NavHostController){
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
            ButtonFilled("Démarrer", navController)
        }
    }
}

@Composable
fun ImageProfil() {
    /*
    Image(painter = painterResource(R.mipmap.image), contentDescription = "profil image", contentScale = ContentScale.Crop, modifier = Modifier.size(200.dp).clip(
        CircleShape
    ))
     */
}

@Composable
fun Socials(){
    Row(modifier = Modifier.padding(end = 38.dp)) {
        Icon(Icons.Rounded.Email, contentDescription = "Localized description", modifier= Modifier.padding(start = 12.dp))
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "bastien.hatinguais@gmail.com")
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        /*Image(
            painterResource(R.mipmap.linkedin_foreground),
            contentDescription = "Linkedin Icon",
            modifier = Modifier
                .requiredSize(50.dp)
                .padding(0.dp)
        )
        */
        Text(text = "www.linkedin.com/bastien-hatinguais")
    }
}

@Composable
fun ButtonFilled(nom : String, navController: NavHostController) {
    Button(onClick = {
        navController.navigate("films")
    }) {
        Text(nom)
    }
}
