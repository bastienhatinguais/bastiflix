package com.example.bastiflix.pages

import VideoPlayer
import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bastiflix.MainViewModel
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.bastiflix.component.*
import com.example.bastiflix.model.ItemGrid
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MovieDetail(id: String?, navController: NavHostController) {
    val viewModel: MainViewModel = viewModel();
    val movie by viewModel.movie.collectAsState();
    val cast by viewModel.cast.collectAsState()
    val reviews by viewModel.reviews.collectAsState();

    if (id != null) {
        viewModel.getMovieDetail(id);
        if (movie == null) {
            Spinner();
        } else {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(elevation = 4.dp, modifier = Modifier
                    .padding(5.dp)
                    .heightIn(150.dp, 200.dp)) {
                    AsyncImage(model = "https://image.tmdb.org/t/p/original" + movie!!.backdrop_path,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.drawWithCache {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black),
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.Multiply)
                            }
                        })
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = if (!movie!!.poster_path.isNullOrEmpty()) "https://image.tmdb.org/t/p/w500" + movie!!.poster_path else "https://aeroclub-issoire.fr/wp-content/uploads/2020/05/image-not-found-300x225.jpg",
                            contentDescription = null,
                            modifier = Modifier
                                .width(100.dp)
                                .clip(
                                    RoundedCornerShape(10.dp),
                                )
                        )
                        Column(
                            Modifier
                                .padding(10.dp)
                                .fillMaxHeight(), verticalArrangement = Arrangement.Center
                        ) {
                            Box() {
                                Text(
                                    text = movie!!.title,
                                    style = MaterialTheme.typography.h5,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .offset(
                                            x = 1.dp,
                                            y = 1.dp
                                        )
                                        .alpha(0.75f)
                                )
                                Text(
                                    text = movie!!.title,
                                    style = MaterialTheme.typography.h5,
                                    color = Color.White,
                                )
                            }

                            Spacer(modifier = Modifier.height(2.dp))
                            Text(movie!!.release_date, fontStyle = Italic)
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Grade(movie!!.vote_average / 2) ;
                                Text(" (${movie!!.vote_count})")
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Text("Genres :", style = MaterialTheme.typography.subtitle2)
                            Row(
                                modifier = Modifier.horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            )
                            {
                                movie!!.genres.forEach { it ->
                                    SuggestionChip(label = { Text(it.name) }, onClick = {});

                                }
                            }

                        }
                    }
                }
                Subtitle(text = "Synopsis :")
                Text(text = movie!!.overview, modifier = Modifier.padding(horizontal = 10.dp))
                Divider(
                    modifier = Modifier.padding(horizontal = 50.dp, vertical = 10.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colors.secondary
                )
                Subtitle(text = "Avec :")
                if (cast.isNullOrEmpty()) {
                    Spinner();
                } else {
                    LazyVerticalGridDemo(cast!!.map {
                        ItemGrid(
                            titre = it.name,
                            imageURL = it.profile_path,
                            id = it.id,
                            route = "people"
                        )
                    }, navController = navController, Modifier.requiredHeight(350.dp))
                }
                Divider(
                    modifier = Modifier.padding(horizontal = 50.dp, vertical = 10.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colors.secondary
                )
                Subtitle(text = "Avis :")
                ReviewList(reviews = reviews);
            }
        }
    } else {
        Text(text = "Une erreur s'est produite, veuillez retourner à la page précédente.")
    }
}
