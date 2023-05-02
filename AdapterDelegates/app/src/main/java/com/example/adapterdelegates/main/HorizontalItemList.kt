package com.example.adapterdelegates.main

import com.example.adapterdelegates.ListItem

data class HorizontalItemList(
    val items: List<ListItem>
): ListItem {
}