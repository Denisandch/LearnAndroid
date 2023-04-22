package com.example.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testing.databinding.ActivityMainBinding
import com.example.testing.viewmodel.AppViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<AppViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }
}