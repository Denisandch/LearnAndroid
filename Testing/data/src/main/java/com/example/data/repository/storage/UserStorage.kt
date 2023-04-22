package com.example.data.repository.storage

import com.example.data.model.UserData

interface UserStorage {
    fun saveUser(user: UserData)
    fun getUser(): UserData
}