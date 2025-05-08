package com.anvipus.explore.kmp.mapper

import com.anvipus.explore.kmp.data.model.Movie
import com.anvipus.explore.kmp.db.Movies

//import com.anvipus.explore.kmp.data.model.Movie
//import com.anvipus.explore.kmp.db.Movies

fun Movie.toEntity(): Movies = Movies(
    id = this.id?.toLong() ?: 0L,
    adult = this.adult?.let { if (it) 1L else 0L },
    backdrop_path = this.backdropPath,
    original_language = this.originalLanguage,
    original_title = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    poster_path = this.posterPath,
    release_date = this.releaseDate,
    title = this.title,
    video = this.video?.let { if (it) 1L else 0L },
    vote_average = this.voteAverage,
    vote_count = this.voteCount
)

fun Movies.toMovie(): Movie = Movie(
    id = this.id.toInt(),
    adult = this.adult?.let { it == 1L },
    backdropPath = this.backdrop_path,
    originalLanguage = this.original_language,
    originalTitle = this.original_title,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.poster_path,
    releaseDate = this.release_date,
    title = this.title,
    video = this.video?.let { it == 1L },
    voteAverage = this.vote_average,
    voteCount = this.vote_count
)
