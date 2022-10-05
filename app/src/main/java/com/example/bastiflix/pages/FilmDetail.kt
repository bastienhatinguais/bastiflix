package com.example.bastiflix.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.bastiflix.MainViewModel
import com.example.bastiflix.component.Spinner

@Composable
fun FilmDetail (id: String?)
{
    val viewModel: MainViewModel = viewModel();
    val movie by viewModel.movie.collectAsState()

    if(id != null){
        viewModel.getMovieDetail(id);
        if(movie == null){
            Spinner();
        }
        else{
            TopAppBar(title = {
                Text(
                    text = movie!!.title,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
            })
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500" + movie!!.poster_path),
                contentDescription = null,
                modifier = Modifier.size(height = 128.dp, width = 70.dp)
            )
            Text("Genres :")
            movie!!.genres.forEach { g -> Text(g.name) }
        }
    }
    else{
        Text(text = "Une erreur s'est produite, veuillez retourner à la page précédente.")
    }
}