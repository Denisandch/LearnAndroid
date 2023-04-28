package com.example.pagingtest.UserService

import com.example.pagingtest.room.User

interface UserService {
    suspend fun getAllUsers(): List<User>
}