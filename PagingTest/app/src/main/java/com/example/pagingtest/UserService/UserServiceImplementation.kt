package com.example.pagingtest.UserService

import android.content.Context
import android.util.Log
import com.example.pagingtest.room.User
import com.example.pagingtest.room.UserDao
import com.example.pagingtest.room.UserDatabase

class UserServiceImplementation(private val context: Context): UserService {

    private val userDao: UserDao = UserDatabase.getDatabase(context).userDao()

    override suspend fun getAllUsersById(limit: Int, offset: Int): List<User> {
        Log.i("q0.", "NEW QUERY")
        return userDao.getAllUsersById(limit, offset)
    }
}