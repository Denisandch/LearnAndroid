package com.example.domain.repository

import com.example.domain.models.UserName

interface Repository {
    fun getUserName(): UserName
    fun saveUserName(user: UserName)
}