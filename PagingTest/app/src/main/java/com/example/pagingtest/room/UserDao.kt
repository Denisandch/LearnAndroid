package com.example.pagingtest.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from all_tasks order by id asc")
    fun getAllTasks(): LiveData<List<User>>

}