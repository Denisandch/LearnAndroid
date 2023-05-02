package com.example.adapterdelegates.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adapterdelegates.ListItem
import com.example.adapterdelegates.databinding.ActivityMainBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

val listOfItemsHorizontal = listOf<HorizontalOneItem>(
    HorizontalOneItem("first", "first desc"),
    HorizontalOneItem("second", "second desc"),
    HorizontalOneItem("third", "third desc")
)

val listOfItemsVertical = listOf<VerticalOneItem>(
    VerticalOneItem("first", "first desc"),
    VerticalOneItem("second", "second desc"),
    VerticalOneItem("third", "third desc")
)

class MainActivity : AppCompatActivity() {

    private val adapter = ListDelegationAdapter<List<ListItem>>(
        MainScreenDelegates.cardsHorizontalDelegate
    )

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            recycler.adapter = adapter

            adapter.apply {
                items = listOf(
                    HorizontalItemList(listOfItemsHorizontal),
                    HorizontalItemList(listOfItemsVertical)
                )
                notifyDataSetChanged()
            }
        }
    }
}