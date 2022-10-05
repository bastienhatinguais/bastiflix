package com.example.bastiflix

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bastiflix.api.Tmdb
import com.example.bastiflix.model.Movie
import com.example.bastiflix.model.MovieDetail
import com.example.bastiflix.model.MovieResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Optional.empty

const val API_KEY : String = "51b9ce8344d23e1aebd50edecb34ec9c";

class MainViewModel : ViewModel() {

    val movies = MutableStateFlow<List<Movie>>(listOf());
    val movie = MutableStateFlow<MovieDetail?>(null);


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build();

    private val service = retrofit.create(Tmdb::class.java)

    fun getMovies() {
        viewModelScope.launch {
            movies.value = service.lastMovies(API_KEY).results;
        }
    }

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            movie.value = service.detailMovie(id, API_KEY);
        }
    }

}