package com.example.bastiflix

import Review
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bastiflix.api.Tmdb
import com.example.bastiflix.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Optional.empty

const val API_KEY : String = "51b9ce8344d23e1aebd50edecb34ec9c";

class MainViewModel : ViewModel() {

    val movies = MutableStateFlow<List<Movie>>(listOf());
    val movie = MutableStateFlow<MovieDetail?>(null);
    val cast = MutableStateFlow<List<Acteur>?>(listOf());

    val peoples = MutableStateFlow<List<People>>(listOf());
    val people = MutableStateFlow<PeopleDetail?>(null);
    val casts = MutableStateFlow<PeopleCasts?>(null);

    val series = MutableStateFlow<List<Serie>>(listOf());
    val serie = MutableStateFlow<SerieDetail?>(null);

    val reviews = MutableStateFlow<List<Review>?>(null);

    val isVideoLoading = MutableStateFlow<Boolean>(true);

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

    fun searchMovie(search: String){
            viewModelScope.launch {
                movies.value = service.searchMovie(search, API_KEY).results;
            }
    }

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            movie.value = service.detailMovie(id, API_KEY);
            cast.value = service.castMovie(id, API_KEY).cast;
            reviews.value = service.reviewsMovie(id, API_KEY).results
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            series.value = service.popularSeries(API_KEY).results;
        }
    }

    fun getSerieDetail(id: String) {
        viewModelScope.launch {
            serie.value = service.detailSerie(id, API_KEY);
            cast.value = service.castSerie(id, API_KEY).cast;
            reviews.value = service.reviewsSerie(id, API_KEY).results;
        }
    }

    fun searchSerie(search: String){
        viewModelScope.launch {
            series.value = service.searchSerie(search, API_KEY).results;
        }
    }

    fun getPeoples() {
        viewModelScope.launch {
            peoples.value = service.trendingPeople(API_KEY).results;
        }
    }

    fun getPeopleDetail(id: String) {
        viewModelScope.launch {
            people.value = service.detailPeople(id, API_KEY);
            casts.value = service.castPeople(id, API_KEY);
        }
    }

    fun searchPeople(search: String){
        viewModelScope.launch {
            peoples.value = service.searchPerson(search, API_KEY).results;
        }
    }

    fun getReviewsMovie(id: String){
        viewModelScope.launch {
            reviews.value = service.reviewsMovie(id, API_KEY).results;
        }
    }
}