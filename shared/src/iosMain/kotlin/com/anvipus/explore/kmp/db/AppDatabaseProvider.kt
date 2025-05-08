package com.anvipus.explore.kmp.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual fun getDatabase(): AppDatabase {
    val driver: SqlDriver = NativeSqliteDriver(AppDatabase.Schema, "movie.db")
    return AppDatabase(driver)
}
