package com.example.pagingtest.UserService

import androidx.paging.PagingData
import com.example.pagingtest.model.User
import kotlinx.coroutines.flow.Flow

interface UserService {
    fun getAllUsersById(filter: String): Flow<PagingData<User>>
}