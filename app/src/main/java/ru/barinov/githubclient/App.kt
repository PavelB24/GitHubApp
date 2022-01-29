package ru.barinov.githubclient

import android.app.Application
import org.koin.android.ext.koin.*
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {


    override fun onCreate() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule))
        }
        super.onCreate()
    }
}