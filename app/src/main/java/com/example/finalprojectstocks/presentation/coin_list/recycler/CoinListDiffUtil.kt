package com.example.finalprojectstocks.presentation.coin_list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.finalprojectstocks.domain.models.Coin

class CoinListDiffUtil(
    private val oldList: List<Coin>,
    private val newList: List<Coin>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.name == newItem.name &&
                oldItem.symbol == newItem.symbol &&
                oldItem.thumb == newItem.thumb
    }
}