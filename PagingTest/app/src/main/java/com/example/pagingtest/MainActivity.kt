package com.example.pagingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pagingtest.databinding.ActivityMainBinding
import com.example.pagingtest.room.User
import com.example.pagingtest.room.UserDao
import com.example.pagingtest.room.UserDatabase
import kotlinx.coroutines.*
import java.sql.Time

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var userDao: UserDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDao = UserDatabase.getDatabase(application).userDao()

        binding.asc.setOnClickListener {
            getAllUsersASC()
        }

        binding.desc.setOnClickListener {
            getAllUsersDESC()
        }

        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0..1001000) {
                userDao.addUser(User(0, name = "Denis $i"))

                Log.i("cor", "$i")
            }
        }

    }

    fun getAllUsersDESC() {
        CoroutineScope(Dispatchers.IO).launch {
            var users = userDao.getAllUsersDESC()
            Log.i("lst", "${users.size}")
        }
    }

    fun getAllUsersASC() {
        CoroutineScope(Dispatchers.IO).launch {
            var users = userDao.getAllUsersASC()
            Log.i("lst", "${users.size}")
        }
    }
}