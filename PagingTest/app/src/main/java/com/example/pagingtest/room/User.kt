package com.example.pagingtest.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "all_tasks")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)
