package com.example.bastiflix.pages

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bastiflix.MainViewModel
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bastiflix.MainActivity
import java.lang.Error

@Composable
fun Films(navController: NavHostController) {
    val viewModel: MainViewModel = viewModel();
    val movies by viewModel.movies.collectAsState()
    if(movies.isEmpty()){
        viewModel.getMovies();
    }

    Text(text = "FILMS")
    Column{
        movies.forEach{movie -> Text(movie.title)}
    }

}
