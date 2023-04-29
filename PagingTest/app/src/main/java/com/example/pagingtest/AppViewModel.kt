package com.example.pagingtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.pagingtest.UserPageSource.UserPageSource
import com.example.pagingtest.UserService.UserService
import com.example.pagingtest.room.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

const val DEFAULT_FILTER = "id"
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class AppViewModel(
    private val userService: UserService
) : ViewModel() {

    val users: Flow<PagingData<User>>

    private val filter = MutableLiveData(DEFAULT_FILTER)

    init {
        users = filter.asFlow()
            .debounce(500)
            .flatMapLatest {
                userService.getAllUsersById(it)
            }
            .cachedIn(viewModelScope)
    }

    fun setFilter(value: String) {
        if (this.filter.value == value) return
        this.filter.value = value
    }
}