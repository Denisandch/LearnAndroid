package com.example.pagingtest.UserService

import androidx.paging.PagingData
import com.example.pagingtest.room.User
import kotlinx.coroutines.flow.Flow

interface UserService {
    fun getAllUsersById(filter: String): Flow<PagingData<User>>
}