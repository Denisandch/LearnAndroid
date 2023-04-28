package com.example.pagingtest.UserService

import com.example.pagingtest.room.User

interface UserService {
    suspend fun getAllUsersById(limit: Int, offset: Int): List<User>
}