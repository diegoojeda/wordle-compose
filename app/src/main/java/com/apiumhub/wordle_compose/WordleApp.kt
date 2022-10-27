package com.apiumhub.wordle_compose

import android.app.Application
import com.apiumhub.wordle_compose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WordleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WordleApp)
            modules(appModule)
        }
    }
}