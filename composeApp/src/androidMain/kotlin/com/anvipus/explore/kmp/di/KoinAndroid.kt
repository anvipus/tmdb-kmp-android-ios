package com.anvipus.explore.kmp.di

import android.app.Application
import com.anvipus.explore.kmp.db.MovieDatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import com.anvipus.explore.kmp.di.commonModule
import org.koin.dsl.module

fun initKoinAndroid(app: Application, appDeclaration: KoinAppDeclaration = {}) = startKoin {
    androidContext(app)
    appDeclaration()
    modules(
        module {
            single { MovieDatabaseDriverFactory(androidContext()) }
        },
        *commonModule.toTypedArray(),
        databaseModule
    )
}
