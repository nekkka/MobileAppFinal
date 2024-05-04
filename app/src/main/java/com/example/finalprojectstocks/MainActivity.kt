package com.example.finalprojectstocks

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.finalprojectstocks.adapters.StocksAdapter
import com.example.finalprojectstocks.databinding.ActivityMainBinding
import com.example.finalprojectstocks.model.entity.Stocks
import com.example.finalprojectstocks.model.network.APIClient
import com.example.finalprojectstocks.ui.dashboard.DashboardFragment
import com.example.finalprojectstocks.ui.home.HomeFragment
import com.example.finalprojectstocks.ui.notifications.NotificationsFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: StocksAdapter by lazy {
        StocksAdapter()
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    switchFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    switchFragment(DashboardFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    switchFragment(NotificationsFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

//    private fun switchFragment(fragment: Fragment) {
//        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//        supportFragmentManager.beginTransaction()
//            .replace(android.R.id.content, fragment)
//            .commit()
//    }

//    private fun switchFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(android.R.id.content, fragment)
//            .commit()
//    }

//    private fun switchFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(android.R.id.content, fragment)
//            .commitAllowingStateLoss()
//    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_main, fragment)
            .addToBackStack(null) // Добавляет транзакцию в стек возврата
            .commit()
    }



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.stockList.adapter = adapter
//
//        binding.searchView.clearFocus()
//        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
//            override fun onQueryTextChange(newText: String): Boolean {
//                return true
//            }
//
//            override fun onQueryTextSubmit(query: String): Boolean {
//                binding.searchView.clearFocus()
//                fetchStocks(query)
//                return false
//            }
//        })
//
//
//
//        val navView: BottomNavigationView = binding.navView
//        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
//
//        switchFragment(HomeFragment())
//
//
//
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val navView: BottomNavigationView = binding.navView
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

//        navView.setOnNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.navigation_home -> {
//                    navController.navigate(R.id.navigation_home)
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.navigation_dashboard -> {
//                    navController.navigate(R.id.navigation_dashboard)
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.navigation_notifications -> {
//                    navController.navigate(R.id.navigation_notifications)
//                    return@setOnNavigationItemSelectedListener true
//                }
//            }
//            false
//        }
    }

    private fun fetchStocks(name: String) {
        val client = APIClient.instance
        val response = client.fetchStocksList(name)

        response.enqueue(object : Callback<Stocks> {
            override fun onResponse(call: Call<Stocks>, response: Response<Stocks>) {
                println("HttpResponse: ${response.body()}")
                val stock: Stocks? = response.body()
                val stocks: MutableList<Stocks> = mutableListOf()
                stock?.let {
                    stocks.add(it)

                }
                adapter.submitList(stocks)
            }

            override fun onFailure(call: Call<Stocks>, t: Throwable) {
                println("HttpResponse: ${t.message}")
                adapter.submitList(emptyList())
            }
        })
    }

}