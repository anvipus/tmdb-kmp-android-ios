package com.anvipus.explore.kmp.db

import com.squareup.sqldelight.db.SqlDriver

expect class MovieDatabaseDriverFactory {
    fun create(): SqlDriver
}
