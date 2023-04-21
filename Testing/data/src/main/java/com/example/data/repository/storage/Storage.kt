package com.example.data.repository.storage

import com.example.data.models.UserNameData

interface Storage {
    fun getUserName(): UserNameData
    fun saveUserName(user: UserNameData)
}