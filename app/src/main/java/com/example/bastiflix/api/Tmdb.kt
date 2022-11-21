package com.example.bastiflix.api

import ReviewResult
import com.example.bastiflix.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface Tmdb{
    @GET("trending/movie/week")
    suspend fun lastMovies(@Query("api_key") api_key: String): MovieResult

    @GET("movie/{id}?language=fr-FR")
    suspend fun detailMovie(@Path("id") id: String, @Query("api_key") api_key: String): MovieDetail

    @GET("movie/{id}/credits?language=fr-FR")
    suspend fun castMovie(@Path("id") id: String, @Query("api_key") api_key: String): MovieCast

    @GET("search/movie?language=fr-FR")
    suspend fun searchMovie(@Query("query") search: String, @Query("api_key") api_key: String): MovieResult

    @GET("tv/popular?language=fr-FR")
    suspend fun popularSeries(@Query("api_key") api_key: String): SerieResult

    @GET("tv/{id}?language=fr-FR")
    suspend fun detailSerie(@Path("id") id: String, @Query("api_key") api_key: String): SerieDetail

    @GET("tv/{id}/credits?language=fr-FR")
    suspend fun castSerie(@Path("id") id: String, @Query("api_key") api_key: String): MovieCast

    @GET("search/tv?language=fr-FR")
    suspend fun searchSerie(@Query("query") search: String, @Query("api_key") api_key: String): SerieResult

    @GET("trending/person/week")
    suspend fun trendingPeople(@Query("api_key") api_key: String): PeopleResult

    @GET("person/{id}?language=fr-FR")
    suspend fun detailPeople(@Path("id") id: String, @Query("api_key") api_key: String): PeopleDetail

    @GET("person/{id}/movie_credits")
    suspend fun castPeople(@Path("id") id: String, @Query("api_key") api_key: String): PeopleCasts

    @GET("search/person?language=fr-FR")
    suspend fun searchPerson(@Query("query") search: String, @Query("api_key") api_key: String): PeopleResult

    //nécessaire car pas de commentaires fr
    @GET("movie/{id}/reviews?language=en-EN")
    suspend fun reviewsMovie(@Path("id") id: String, @Query("api_key") api_key: String): ReviewResult

    //nécessaire car pas de commentaires fr
    @GET("tv/{id}/reviews?language=en-EN")
    suspend fun reviewsSerie(@Path("id") id: String, @Query("api_key") api_key: String): ReviewResult

}