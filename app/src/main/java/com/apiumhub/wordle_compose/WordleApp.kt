package com.apiumhub.wordle_compose

import android.app.Application
import com.apiumhub.ktor_server.MainServer
import com.apiumhub.wordle_compose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WordleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MainServer.start()
        startKoin {
            androidContext(this@WordleApp)
            modules(appModule)
        }
    }
}