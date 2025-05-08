package com.anvipus.explore.kmp.db

import com.anvipus.explore.kmp.AppContextHolder

actual fun getDatabase(): AppDatabase {
    return AppDatabase(
        MovieDatabaseDriverFactory(AppContextHolder.context).create()
    )
}
