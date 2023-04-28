package com.example.pagingtest.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("select * from user order by id desc")
    fun getAllUsersDESC(): List<User>

    @Query("select * from user order by id asc")
    fun getAllUsersASC(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)
}