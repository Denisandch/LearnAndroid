package com.example.pagingtest.UserPageSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingtest.UserService.UserService
import com.example.pagingtest.room.User
import com.example.pagingtest.room.UserDao

class UserPageSource(
    private val userService: UserService
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {

        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {

        val page: Int = params.key ?: 0
        val pageSize: Int = params.loadSize.coerceAtMost(30)

        return try {
            val response = userService.getAllUsersById(pageSize, page * pageSize)

            val nextKey = if (response.size < pageSize) null else page + 1
            val prevKey = if (page == 0) null else page - 1

            LoadResult.Page(response, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}