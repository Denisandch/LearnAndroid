package com.example.domain.usecases

import com.example.domain.model.User
import com.example.domain.repository.UserRepository

class SaveUserUseCase(
    private val repository: UserRepository
) {
    fun saveUser(user: User) {
        repository.saveUser(user)
    }
}