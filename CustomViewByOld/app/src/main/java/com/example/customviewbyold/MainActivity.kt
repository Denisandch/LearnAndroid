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


        binding.custom.setListeners {
            if (it == BottomButtonAction.BUTTON) {
                binding.custom.buttonText = "Новый текст"
                Toast.makeText(this, binding.custom.buttonText, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Text", Toast.LENGTH_SHORT).show()
            }
        }
    }
}