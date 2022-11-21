package com.example.bastiflix.pages

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bastiflix.MainViewModel
import com.google.accompanist.flowlayout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.bastiflix.component.*
import com.example.bastiflix.model.ItemGrid
import com.example.bastiflix.model.SerieDetail
import com.google.android.material.chip.Chip
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SerieDetail(id: String?, navController: NavHostController) {
    val viewModel: MainViewModel = viewModel();
    val serie by viewModel.serie.collectAsState();
    val cast by viewModel.cast.collectAsState()
    val reviews by viewModel.reviews.collectAsState()
    val scrollState = rememberScrollState()

    if (id != null) {
        viewModel.getSerieDetail(id);
        if (serie == null) {
            Spinner();
        } else {
            Column(
                modifier = Modifier.verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(elevation = 4.dp, modifier = Modifier
                    .padding(5.dp)
                    .heightIn(150.dp, 200.dp)) {
                    AsyncImage(model = "https://image.tmdb.org/t/p/original" + serie!!.backdrop_path,
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
                            model = if (!serie!!.poster_path.isNullOrEmpty()) "https://image.tmdb.org/t/p/w500" + serie!!.poster_path else "https://aeroclub-issoire.fr/wp-content/uploads/2020/05/image-not-found-300x225.jpg",
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
                                    text = serie!!.name,
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
                                    text = serie!!.name,
                                    style = MaterialTheme.typography.h5,
                                    color = Color.White,
                                )
                            }

                            Spacer(modifier = Modifier.height(2.dp))
                            Text(serie!!.first_air_date, fontStyle = Italic)
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Grade(serie!!.vote_average / 2);
                                Text(" (${serie!!.vote_count})")
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Text("Genres :", style = MaterialTheme.typography.subtitle2)
                            Row(
                                modifier = Modifier.horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            )
                            {
                                serie!!.genres.forEach { it ->
                                    SuggestionChip(label = { Text(it.name) }, onClick = {});

                                }
                            }

                        }
                    }
                }
                Subtitle(text = "Synopsis :")
                Text(text = serie!!.overview, modifier = Modifier.padding(horizontal = 10.dp))

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
                Subtitle(text = "Avis :")
                ReviewList(reviews = reviews);

            }
        }
    } else {
        Text(text = "Une erreur s'est produite, veuillez retourner à la page précédente.")
    }
}
