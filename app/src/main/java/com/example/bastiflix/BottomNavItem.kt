package com.example.bastiflix

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title:String, val icon: ImageVector, var route:String){

    object Films : BottomNavItem("Films", Icons.Default.Home,"films")
    object Series: BottomNavItem("Séries",Icons.Default.Settings,"series")
    object Acteurs: BottomNavItem("Acteurs", Icons.Default.Settings,"acteurs")
}