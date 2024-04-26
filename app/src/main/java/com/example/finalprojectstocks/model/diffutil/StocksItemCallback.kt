package com.example.finalprojectstocks.model.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.finalprojectstocks.model.entity.Stocks

class StocksItemCallback : DiffUtil.ItemCallback<Stocks>(){
    override fun areItemsTheSame(oldItem: Stocks, newItem: Stocks): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Stocks, newItem: Stocks): Boolean {
        return oldItem.ticker == newItem.ticker && oldItem.name == newItem.name
    }
}