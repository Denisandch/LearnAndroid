package com.example.adapterdelegates.main

import com.example.adapterdelegates.ListItem
import com.example.adapterdelegates.databinding.HorizontalListOneBinding
import com.example.adapterdelegates.databinding.ItemHorizontalBinding
import com.example.adapterdelegates.databinding.ItemVerticalBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding



object MainScreenDelegates {
    val cardsHorizontalDelegate = adapterDelegateViewBinding<HorizontalItemList, ListItem, HorizontalListOneBinding>(
        { inflater, container -> HorizontalListOneBinding.inflate(inflater, container, false) }
    ) {
        bind {
            binding.horizontalRecycler.adapter = HorizontalAdapter
            (binding.horizontalRecycler.adapter as ListDelegationAdapter<List<ListItem>>).apply {
                items = item.items
                notifyDataSetChanged()
            }

        }
    }

    val horizontalItemDelegate = adapterDelegateViewBinding<HorizontalOneItem, ListItem, ItemHorizontalBinding>(
        { inflater, container -> ItemHorizontalBinding.inflate(inflater, container, false) }
    ) {
        bind {
            binding.name.text = item.name
            binding.description.text = item.description
        }
    }

    val verticalItemDelegate = adapterDelegateViewBinding<VerticalOneItem, ListItem, ItemVerticalBinding>(
        { inflater, container -> ItemVerticalBinding.inflate(inflater, container, false) }
    ) {
        bind {
            binding.name.text = item.name
            binding.description.text = item.description
        }
    }

    private val HorizontalAdapter = ListDelegationAdapter(
        MainScreenDelegates.horizontalItemDelegate,
        MainScreenDelegates.verticalItemDelegate
    )
}