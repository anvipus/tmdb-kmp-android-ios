package com.anvipus.explore.kmp.di

import com.anvipus.explore.kmp.db.AppDatabase
import com.anvipus.explore.kmp.db.MovieDatabaseDriverFactory
import org.koin.dsl.module

val databaseModule = module {
    single {
        val driver = get<MovieDatabaseDriverFactory>().create()
        AppDatabase(driver)
    }
    single { get<AppDatabase>().moviesQueries }
}
