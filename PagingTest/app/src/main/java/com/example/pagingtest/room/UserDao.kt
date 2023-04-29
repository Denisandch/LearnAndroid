package com.example.pagingtest.room

import android.database.sqlite.SQLiteQuery
import androidx.room.*
import java.sql.SQLInput

@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY id DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllUsersById(limit: Int, offset: Int): List<User>

    @Query("SELECT * FROM user ORDER BY name DESC LIMIT :limit OFFSET :offset")
    suspend fun getAllUsersByName(limit: Int, offset: Int): List<User>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)
}