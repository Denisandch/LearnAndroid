package com.example.testing.app.di

import com.example.testing.viewmodel.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<AppViewModel> {
        AppViewModel(
            saveUserUseCase = get(),
            getUserUseCase = get()
        )
    }
}