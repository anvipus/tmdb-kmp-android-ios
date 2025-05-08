package com.anvipus.explore.kmp.di

import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule: List<Module> = listOf(
    databaseModule,
    // tambahkan module lain di sini
)
