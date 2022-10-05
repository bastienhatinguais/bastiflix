package com.example.bastiflix.component

import android.media.Image
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
fun LazyVerticalGridDemo(items: List<ItemGrid>, navController: NavHostController?){
    val list = (1..10).map { it.toString() }

    LazyVerticalGrid(
        GridCells.Adaptive(128.dp),
        content = {
            items(items.size) { i ->
                Card(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 4.dp, end = 4.dp)
                        .fillMaxSize(),
                    elevation = 8.dp,
                    onClick = {
                        navController?.navigate("film/" + items[i].id)
                    }
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(items[i].imageURL),
                        contentDescription = null,
                        modifier = Modifier.size(height = 128.dp, width = 70.dp)
                    )
                    Text(
                        text = items[i].titre,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    )
}