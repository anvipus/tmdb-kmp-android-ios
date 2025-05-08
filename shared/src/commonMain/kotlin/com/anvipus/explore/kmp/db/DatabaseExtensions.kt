package com.anvipus.explore.kmp.db

val AppDatabase.movieDao: MovieDao
    get() = MovieDaoImpl(this.moviesQueries)
