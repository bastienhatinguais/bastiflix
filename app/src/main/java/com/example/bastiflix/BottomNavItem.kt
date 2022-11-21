package com.example.bastiflix

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title:String, val icon: ImageVector, var route:String){

    object Movies : BottomNavItem("Films", Icons.Default.Movie,"films")
    object Series: BottomNavItem("Séries",Icons.Default.LiveTv,"series")
    object Peoples: BottomNavItem("Personnalités", Icons.Default.Groups,"acteurs")
}