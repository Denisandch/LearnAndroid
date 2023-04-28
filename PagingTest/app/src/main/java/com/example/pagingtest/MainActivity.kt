package com.example.pagingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pagingtest.adapters.UserAdapter
import com.example.pagingtest.databinding.ActivityMainBinding
import com.example.pagingtest.room.User
import com.example.pagingtest.room.UserDao
import com.example.pagingtest.room.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    lateinit var adapter: UserAdapter
    lateinit var binding: ActivityMainBinding
    private val sharedViewModel by viewModel<AppViewModel>()

    lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDao = UserDatabase.getDatabase(application).userDao()
        adapter = UserAdapter(this)

//        binding.asc.setOnClickListener {
//            getAllUsersASC()
//        }

//        binding.desc.setOnClickListener {
//            getAllUsersDESC()
//        }


//        CoroutineScope(Dispatchers.IO).launch {
//            for (i in 0..10000) {
//                userDao.addUser(User(0, name = "${10000 - i}"))
//
//                Log.i("cor", "$i")
//            }
//        }

        binding.userRecycler.adapter = adapter


        CoroutineScope(Dispatchers.IO).launch {
            sharedViewModel.users.collectLatest(adapter::submitData)
        }

        //getAllUsersDESC()
    }

//    fun getAllUsersDESC() {
//        CoroutineScope(Dispatchers.IO).launch {
//            var users = userDao.getAllUsersDESC()
//            Log.i("lst", "${users.size}")
//        }
//    }

//    fun getAllUsersASC() {
//        CoroutineScope(Dispatchers.IO).launch {
//            var users = userDao.getAllUsersASC()
//            Log.i("lst", "${users.size}")
//        }
//    }
}