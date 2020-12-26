package ru.work.avitorecycler

import androidx.recyclerview.widget.DiffUtil
import ru.work.avitorecycler.data.Item

class ItemsCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}