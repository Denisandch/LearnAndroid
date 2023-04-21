package com.example.testing.app.di

import com.example.data.repository.RepositoryImplementation
import com.example.data.repository.storage.Storage
import com.example.data.repository.storage.StorageImplementation
import com.example.domain.repository.Repository
import org.koin.dsl.module

val dataModule = module {

    single<Storage> {
        StorageImplementation()
    }
    single<Repository> {
        RepositoryImplementation(storage = get())
    }
}