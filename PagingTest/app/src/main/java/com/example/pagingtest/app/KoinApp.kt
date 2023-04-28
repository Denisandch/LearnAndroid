package com.example.pagingtest.app

import android.app.Application
import com.example.pagingtest.room.UserDao
import com.example.pagingtest.room.UserDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinApp)
            modules(listOf(appModule))
        }
    }
}