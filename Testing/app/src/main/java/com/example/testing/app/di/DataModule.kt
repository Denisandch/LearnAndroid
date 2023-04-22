package com.example.testing.app.di

import com.example.data.repository.UserRepositoryImplementation
import com.example.data.repository.storage.UserStorage
import com.example.data.repository.storage.UserStorageImplementation
import com.example.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<UserStorage> {
        UserStorageImplementation()
    }

    single<UserRepository> {
        UserRepositoryImplementation(storage = get())
    }
}