package com.example.domain.usecases

import com.example.domain.model.User
import com.example.domain.repository.UserRepository

class GetUserUseCase(
    private val repository: UserRepository
) {
    fun getUser(): User {
        return repository.getUser()
    }
}