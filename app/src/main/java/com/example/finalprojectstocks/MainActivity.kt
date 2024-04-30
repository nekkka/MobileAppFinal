package com.example.finalprojectstocks

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.finalprojectstocks.adapters.StocksAdapter
import com.example.finalprojectstocks.databinding.ActivityMainBinding
import com.example.finalprojectstocks.model.entity.Stocks
import com.example.finalprojectstocks.model.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: StocksAdapter by lazy {
        StocksAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.stockList.adapter = adapter

        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                binding.searchView.clearFocus()
                fetchStocks(query)
                return false
            }
        })


    }

    private fun fetchStocks(name: String) {
        val client = APIClient.instance
        val response = client.fetchKotikList(name)

        response.enqueue(object : Callback<Stocks> {
            override fun onResponse(call: Call<Stocks>, response: Response<Stocks>) {
                println("HttpResponse: ${response.body()}")
                val cat: Stocks? = response.body()
                val cats: MutableList<Stocks> = mutableListOf()
                cat?.let {
                    cats.add(it)
                }
                adapter.submitList(cats)
            }

            override fun onFailure(call: Call<Stocks>, t: Throwable) {
                println("HttpResponse: ${t.message}")
                adapter.submitList(emptyList())
            }
        })
    }

}