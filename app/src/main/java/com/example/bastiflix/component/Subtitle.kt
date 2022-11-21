package com.example.bastiflix.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Subtitle(text: String){
    Spacer(modifier = Modifier.height(10.dp))
    Text(text, style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.primary
    )
    Spacer(modifier = Modifier.height(5.dp))
}