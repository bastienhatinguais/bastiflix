package com.example.bastiflix.pages

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bastiflix.MainViewModel
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bastiflix.MainActivity
import com.example.bastiflix.component.LazyVerticalGridDemo
import com.example.bastiflix.component.Spinner
import com.example.bastiflix.model.ItemGrid
import java.lang.Error

@Composable
fun Movies(navController: NavHostController) {
    val viewModel: MainViewModel = viewModel();
    val movies by viewModel.movies.collectAsState()
    if (movies.isEmpty()) {
        viewModel.getMovies();
        Spinner();
    } else {
        LazyVerticalGridDemo(movies.map {
            ItemGrid(
                titre = it.title,
                imageURL = it.poster_path,
                id = it.id,
                route = "movie"
            )
        }, navController = navController)
    }
}
