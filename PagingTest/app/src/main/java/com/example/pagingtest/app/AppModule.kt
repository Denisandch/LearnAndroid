package com.example.pagingtest.app

import com.example.pagingtest.AppViewModel
import com.example.pagingtest.UserPageSource.UserPageSource
import com.example.pagingtest.UserService.UserService
import com.example.pagingtest.UserService.UserServiceImplementation
import com.example.pagingtest.room.UserDao
import com.example.pagingtest.room.UserDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<UserService> {
        UserServiceImplementation(get())
    }

    viewModel<AppViewModel> {
        AppViewModel(
            userPageSource = UserPageSource(userService = get())
        )
    }

}