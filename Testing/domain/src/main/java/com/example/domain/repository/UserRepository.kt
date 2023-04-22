package com.example.domain.repository

import com.example.domain.model.User

interface UserRepository {
    fun saveUser(user: User)
    fun getUser(): User
}