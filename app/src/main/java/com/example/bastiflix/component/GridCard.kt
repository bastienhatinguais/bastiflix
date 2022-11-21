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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.bastiflix.model.ItemGrid

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun GridCard(item: ItemGrid, navController: NavHostController?) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(1.dp)
            .defaultMinSize(minHeight = 170.dp),
        onClick = {
            navController?.navigate(item.route + "/" + item.id)
        }
    ) {
        AsyncImage(model = if(!item!!.imageURL.isNullOrEmpty()) "https://image.tmdb.org/t/p/w500" + item!!.imageURL else "https://aeroclub-issoire.fr/wp-content/uploads/2020/05/image-not-found-300x225.jpg",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier. drawWithCache {
                val gradient = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black),
                )
                onDrawWithContent {
                    drawContent()
                    drawRect(gradient, blendMode = BlendMode.Multiply)
                }
            })
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom, modifier = Modifier.padding(5.dp)) {
            Text(
                item.titre,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center
            )
        }
    }

}
