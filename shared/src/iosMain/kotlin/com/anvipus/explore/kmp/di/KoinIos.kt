package com.anvipus.explore.kmp.di

import com.anvipus.explore.kmp.db.MovieDatabaseDriverFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@kotlin.native.ObjCName("initKoinIos")
fun initKoinIos() {
    startKoin {
        modules(
            module {
                single { MovieDatabaseDriverFactory() }
            },
            *commonModule.toTypedArray()
        )
    }
}
