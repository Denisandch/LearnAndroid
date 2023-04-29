package com.example.pagingtest.UserService

import android.content.Context
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pagingtest.UserPageSource.UserPageSource
import com.example.pagingtest.room.User
import com.example.pagingtest.room.UserDao
import com.example.pagingtest.room.UserDatabase
import kotlinx.coroutines.flow.Flow

class UserServiceImplementation(private val context: Context): UserService {

  //  private val userDao: UserDao = UserDatabase.getDatabase(context).userDao()

    override fun getAllUsersById(filter: String): Flow<PagingData<User>> {

        val userPageSource = UserPageSource(context, filter)

        return Pager<Int, User>(
            PagingConfig(pageSize = 30, initialLoadSize = 30),
        ) {
            userPageSource
        }.flow
    }
}