package com.example.customviewbyold

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.customviewbyold.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            customFirst.setButtonListener {
                customFirst.buttonText = "Новый текст"
                Toast.makeText(
                    this@MainActivity,
                    binding.customFirst.buttonText,
                    Toast.LENGTH_SHORT
                ).show()
            }

            customFirst.setTextViewListener {
                customFirst.textViewText = "Новый текст"
                Toast.makeText(
                    this@MainActivity,
                    binding.customFirst.buttonText,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}