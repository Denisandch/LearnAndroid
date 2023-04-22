package com.example.domain.usecases

import com.example.domain.model.User
import com.example.domain.repository.UserRepository

class SaveUserUseCase(
    private val repository: UserRepository
) {
    fun saveUser(user: User): Boolean {

        val oldUser = repository.getUser()

        if (oldUser.name == user.name) return false

        return repository.saveUser(user)
    }
}