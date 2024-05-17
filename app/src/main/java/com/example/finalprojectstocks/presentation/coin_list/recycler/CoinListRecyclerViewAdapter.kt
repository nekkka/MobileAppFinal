package com.example.finalprojectstocks.presentation.coin_list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.finalprojectstocks.databinding.CoinListItemBinding
import com.example.finalprojectstocks.domain.models.Coin

class CoinListRecyclerViewAdapter(
    private val onClick: (coin: Coin)->Unit
): RecyclerView.Adapter<CoinListRecyclerViewAdapter.CoinListViewHolder>() {

    private var coinList = emptyList<Coin>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        return CoinListViewHolder.from(parent, onClick)
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        val currentResult=coinList[position]
        holder.bind(currentResult)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    fun update(newRecipes:List<Coin>){
        val recipesDiffUtils=CoinListDiffUtil(coinList, newRecipes)
        val diffUtilResult= DiffUtil.calculateDiff(recipesDiffUtils)
        coinList=newRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }

    class CoinListViewHolder(private val binding: CoinListItemBinding,  private val onClick: (Coin) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: Coin){
            binding.root.setOnClickListener {
                onClick(coin)
            }
            binding.coinName.text = coin.name
            binding.coinSymbol.text = coin.symbol
            binding.imageView.load(coin.thumb)
        }

        companion object{
            fun from(parent:ViewGroup, onClick: (coin: Coin)->Unit): CoinListViewHolder{
                val layoutInflater= LayoutInflater.from(parent.context)
                val binding=CoinListItemBinding.inflate(layoutInflater, parent, false)
                return CoinListViewHolder(binding, onClick)
            }
        }
    }
}