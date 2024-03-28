package com.udemyAndroid.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.udemyAndroid.shoppinglist.R
import com.udemyAndroid.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_disabled,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        val status = if(shopItem.enabled) {
            "Active"
        } else {
            "Not Active"
        }
        viewHolder.tvName.text = "${shopItem.name} $status"
        viewHolder.tvCount.text = shopItem.count.toString()
        viewHolder.itemView.setOnLongClickListener {
            true
        }
        if (shopItem.enabled) {
            viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.itemView.context, android.R.color.holo_red_dark))
        } else {
            viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.itemView.context, android.R.color.holo_blue_dark))
        }
    }

//    override fun onViewRecycled(viewHolder: ShopItemViewHolder) {
//        super.onViewRecycled(viewHolder)
//        viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.itemView.context, android.R.color.holo_blue_dark))
//
//    }
}