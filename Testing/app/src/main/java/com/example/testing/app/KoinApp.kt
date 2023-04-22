package com.example.testing.app

import android.app.Application
import com.example.testing.app.di.dataModule
import com.example.testing.app.di.domainModule
import com.example.testing.app.di.presentationModule
import org.koin.core.context.startKoin

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(presentationModule, domainModule, dataModule))
        }
    }
}