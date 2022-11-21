package com.example.bastiflix.pages

import VideoPlayer
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.PlayCircleFilled
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bastiflix.component.Spinner


@Composable
fun Accueil(navController: NavHostController, classes: WindowSizeClass) {
    val classeLargeur = classes.widthSizeClass
    when (classeLargeur) {
        WindowWidthSizeClass.Compact -> PageAccueilColonne(navController)
        //WindowWidthSizeClass.Medium -> PageAccueilLigne(navController)
        //WindowWidthSizeClass.Expanded -> PageAccueilLigne(navController)
    }

}

@Composable
fun PageAccueilColonne(navController: NavHostController) {
    VideoPlayer(Uri.parse("https://drive.google.com/u/0/uc?id=12lIVyNi5A6UoQZ6amw8qmkP304LeWK1M&export=download"));
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.size(50.dp))

            Text("Réalisé par :", fontSize = 15.sp)
            Text("Bastien Hatinguais", fontSize = 30.sp)
            Text("Elève en 2ème année de cycle ingénieur en alternance", fontSize = 15.sp)
            Text(
                "Ecole d'ingénieur ISIS - INU Champollion",
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.size(20.dp))
            Socials()
        }
        Column(        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                navController.navigate("films")
            }) {
                Icon(Icons.Rounded.PlayCircleFilled, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.size(5.dp))
                Text("Démarrer", color= Color.White)
            }
            Spacer(modifier = Modifier.size(100.dp))
        }
    }
}

@Composable
fun Socials() {

    Row(modifier = Modifier.padding(end = 38.dp)) {
        Icon(
            Icons.Rounded.Email,
            contentDescription = "Localized description",
            modifier = Modifier.padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "bastien.hatinguais@gmail.com")
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "www.linkedin.com/bastien-hatinguais")
    }
}