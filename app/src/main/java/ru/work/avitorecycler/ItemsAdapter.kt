package ru.work.avitorecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.work.avitorecycler.data.Item

class ItemsAdapter(
    private val clickListener: OnRecyclerDeleteClick
) : ListAdapter<Item, ItemViewHolder>(ItemsCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.findViewById<Button>(R.id.btn_delete).setOnClickListener {
            clickListener.onClick(holder.adapterPosition)
        }
    }

    interface OnRecyclerDeleteClick {
        fun onClick(num: Int)
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val number = itemView.findViewById<TextView>(R.id.tv_number)

    fun bind(item: Item) {
        number.text = item.id.toString()
    }
}