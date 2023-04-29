package com.example.pagingtest.app

import com.example.pagingtest.AppViewModel
import com.example.pagingtest.UserService.UserService
import com.example.pagingtest.UserService.UserServiceImplementation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<UserService> {
        UserServiceImplementation(get())
    }

    viewModel<AppViewModel> {
        AppViewModel(
            userService = get()
        )
    }

}