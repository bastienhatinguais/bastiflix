package com.example.bastiflix.model;


data class SerieResult(
    val page: Int,
    val results: List<Serie>,
    val total_pages: Int,
    val total_results: Int
)

data class Serie(
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
)

data class SerieDetail(
    val adult: Boolean,
    val backdrop_path: String,
    val created_by: List<Any>,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val in_production: Boolean,
    val languages: List<String>,
    val last_air_date: String,
    val last_episode_to_air: LastEpisodeToAir,
    val name: String,
    val networks: List<Network>,
    val next_episode_to_air: NextEpisodeToAir,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val seasons: List<Season>,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    val vote_average: Double,
    val vote_count: Int
)

data class LastEpisodeToAir(
    val air_date: String,
    val episode_number: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    val runtime: Any,
    val season_number: Int,
    val show_id: Int,
    val still_path: Any,
    val vote_average: Double,
    val vote_count: Int
)

data class Network(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class NextEpisodeToAir(
    val air_date: String,
    val episode_number: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    val runtime: Any,
    val season_number: Int,
    val show_id: Int,
    val still_path: Any,
    val vote_average: Double,
    val vote_count: Int
)


data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: Any,
    val season_number: Int
)
