package com.example.mvvmrepeat

import android.app.Application
import com.example.mvvmrepeat.model.UsersService

class App : Application() {
    val usersService = UsersService()
}