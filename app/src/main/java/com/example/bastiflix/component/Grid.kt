package com.example.bastiflix.component

import android.media.Image
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bastiflix.model.ItemGrid

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun LazyVerticalGridDemo(items: List<ItemGrid>, navController: NavHostController?, modifier: Modifier?) {
        LazyVerticalGrid(
            GridCells.Adaptive(100.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = modifier!!,
            content = {
                items(items.size) { i ->
                    GridCard(item = items[i], navController = navController)
                }
            }
        )
}

@Composable
fun LazyVerticalGridDemo(items: List<ItemGrid>, navController: NavHostController?) {
        LazyVerticalGrid(
            GridCells.Adaptive(100.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            content = {
                items(items.size) { i ->
                    GridCard(item = items[i], navController = navController)
                }
            }
        )
}