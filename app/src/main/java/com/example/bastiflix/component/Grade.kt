package com.example.bastiflix.component;

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable;
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.ui.Modifier
import kotlin.math.ceil


@Composable
fun Grade(grade: Double) {
    Row() {
        for (i in 0 until grade.toInt()){
            Icon(Icons.Default.Star, contentDescription = "Star", tint = MaterialTheme.colors.secondary)
        }
        print(ceil(grade));
        if(ceil(grade) > grade){
            Icon(Icons.Default.StarHalf, contentDescription = "Half star", tint = MaterialTheme.colors.secondary)
        }
    }
}
