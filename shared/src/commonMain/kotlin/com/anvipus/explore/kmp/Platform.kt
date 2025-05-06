package com.anvipus.explore.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform