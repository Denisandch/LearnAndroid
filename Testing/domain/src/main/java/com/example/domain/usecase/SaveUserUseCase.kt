package com.example.domain.usecase

import com.example.domain.models.UserName
import com.example.domain.repository.Repository

class SaveUserUseCase(
    private val repository: Repository
) {
    fun saveUser(user: UserName) {
        repository.saveUserName(user)
    }
}