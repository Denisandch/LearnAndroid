package com.example.pagingtest.UserService

import android.content.Context
import com.example.pagingtest.room.User
import com.example.pagingtest.room.UserDao
import com.example.pagingtest.room.UserDatabase

class UserServiceImplementation(private val context: Context): UserService {

    private val userDao: UserDao = UserDatabase.getDatabase(context).userDao()

    override suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsersASC()
    }
}