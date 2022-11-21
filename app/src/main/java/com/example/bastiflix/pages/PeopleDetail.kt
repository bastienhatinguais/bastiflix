package com.example.bastiflix.pages

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
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
import com.example.bastiflix.component.Spinner
import com.google.accompanist.flowlayout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Divider
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.bastiflix.component.Grade
import com.example.bastiflix.component.LazyVerticalGridDemo
import com.example.bastiflix.component.Subtitle
import com.example.bastiflix.model.ItemGrid
import com.example.bastiflix.model.PeopleDetail
import com.google.android.material.chip.Chip
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleDetail(id: String?, navController: NavHostController) {
    val viewModel: MainViewModel = viewModel();
    val people by viewModel.people.collectAsState()
    val casts by viewModel.casts.collectAsState()
    val scrollState = rememberScrollState()

    if (id != null) {
        viewModel.getPeopleDetail(id);

        if (people == null) {
            Spinner();
        } else {

            Column( modifier = Modifier.verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally) {

                Card(elevation = 4.dp, modifier = Modifier
                    .padding(5.dp)
                    .heightIn(150.dp, 200.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = if (!people!!.profile_path.isNullOrEmpty()) "https://image.tmdb.org/t/p/w500" + people!!.profile_path else "https://aeroclub-issoire.fr/wp-content/uploads/2020/05/image-not-found-300x225.jpg",
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
                                    text = people!!.name,
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
                                    text = people!!.name,
                                    style = MaterialTheme.typography.h5,
                                    color = Color.White,
                                )
                            }

                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                people!!.birthday + " - " + if (people!!.deathday != null) people!!.deathday else "Aujourd'hui",
                                fontStyle = Italic
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Grade(people!!.popularity / 10) ;
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Text("Aussi appelé :", style = MaterialTheme.typography.subtitle2)
                            Row(
                                modifier = Modifier.horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            )
                            {
                                people!!.also_known_as.forEach { it ->
                                    SuggestionChip(label = { Text(it) }, onClick = {});
                                }
                            }

                        }
                    }
                }
                Subtitle("Biographie :")
                Text(text = people!!.biography, modifier = Modifier.padding(horizontal = 10.dp))

                Divider(
                    modifier = Modifier.padding(horizontal = 50.dp, vertical = 10.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colors.secondary
                )
                Subtitle("Est apparu dans :")
                if (casts == null) {
                    Spinner();
                } else {
                        LazyVerticalGridDemo(casts!!.cast.map {
                            ItemGrid(
                                titre = it.title,
                                imageURL = it.poster_path,
                                id = it.id,
                                route = "movie"
                            )
                        }, navController = navController, Modifier.requiredHeight(350.dp))
                    }

            }


        }
    } else {
        Text(text = "Une erreur s'est produite, veuillez retourner à la page précédente.")
    }
}
