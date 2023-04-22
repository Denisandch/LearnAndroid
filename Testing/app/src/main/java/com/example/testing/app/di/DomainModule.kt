package com.example.testing.app.di

import com.example.domain.usecases.GetUserUseCase
import com.example.domain.usecases.SaveUserUseCase
import org.koin.dsl.module

val domainModule = module {
    single<GetUserUseCase> {
        GetUserUseCase(repository = get())
    }

    single<SaveUserUseCase> {
        SaveUserUseCase(repository = get())
    }
}