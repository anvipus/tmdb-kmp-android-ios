package com.anvipus.explore.kmp

import android.app.Application
import com.anvipus.explore.kmp.di.initKoinAndroid

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this)
        AppContextHolder.context = this
    }
}
