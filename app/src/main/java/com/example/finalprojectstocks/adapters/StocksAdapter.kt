package com.example.finalprojectstocks.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalprojectstocks.databinding.ItemStocksBinding
import com.example.finalprojectstocks.model.diffutil.StocksItemCallback
import com.example.finalprojectstocks.model.entity.Stocks


class StocksAdapter : ListAdapter<Stocks, StocksAdapter.ViewHolder>(StocksItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStocksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemStocksBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stock: Stocks) {
            with(binding) {
                stockTicker.text = stock.ticker
                stockName.text = stock.name
                stockPrice.text = stock.price.toString()


            }
        }
    }
}
