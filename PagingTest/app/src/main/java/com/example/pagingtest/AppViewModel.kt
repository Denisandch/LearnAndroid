package com.example.pagingtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.pagingtest.UserPageSource.UserPageSource
import com.example.pagingtest.room.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class AppViewModel(
    private val userPageSource: UserPageSource
) : ViewModel() {


    val users: StateFlow<PagingData<User>> = Pager<Int, User>(
        PagingConfig(pageSize = 50),
    ) {
        userPageSource
    }.flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}