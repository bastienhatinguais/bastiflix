package com.example.bastiflix.api

import com.example.bastiflix.model.MovieResult
import retrofit2.http.GET
import retrofit2.http.Query

public interface Tmdb{
    @GET("trending/movie/week")
    suspend fun lastMovies(@Query("api_key") api_key: String): MovieResult
}