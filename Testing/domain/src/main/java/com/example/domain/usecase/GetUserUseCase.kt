package com.example.domain.usecase

import com.example.domain.models.UserName
import com.example.domain.repository.Repository

class GetUserUseCase(
    private val repository: Repository
) {
    fun getUser(): UserName{
        return repository.getUserName()
    }
}