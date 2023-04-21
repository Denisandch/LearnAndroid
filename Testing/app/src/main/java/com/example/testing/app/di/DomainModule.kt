package com.example.testing.app.di

import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.SaveUserUseCase
import org.koin.dsl.module

val domainModule = module {
    single<GetUserUseCase> {
        GetUserUseCase(repository = get())
    }

    single<SaveUserUseCase> {
        SaveUserUseCase(repository = get())
    }
}