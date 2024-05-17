package com.example.finalprojectstocks.presentation.coin_list

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprojectstocks.databinding.FragmentCoinListBinding
import com.example.finalprojectstocks.presentation.UIState
import com.example.finalprojectstocks.presentation.UIStateHandler
import com.example.finalprojectstocks.presentation.coin_list.recycler.CoinListRecyclerViewAdapter
import com.example.finalprojectstocks.presentation.hideError
import com.example.finalprojectstocks.presentation.hideLoading
import com.example.finalprojectstocks.presentation.showError
import com.example.finalprojectstocks.presentation.showLoading
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CoinListFragment : Fragment(), UIStateHandler {
    private val viewModel: CoinListViewModel by viewModels()
    private var _binding: FragmentCoinListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CoinListRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        val view = binding.root
        initData()
        establishRecyclerView()
        observeData()
        return view
    }

    private fun initData() {
        viewModel.getCoinList()
    }


    private fun observeData() {
        lifecycleScope.launch {
            viewModel.coinList.collect {
                Log.e("TAG", "from collect: $it")
                viewModel.coinList.collect { state ->
                    when (state) {
                        is UIState.Loading -> showLoading(binding.loadingView)
                        is UIState.Success -> {
                            hideLoading(binding.loadingView)
                            hideError(binding.errorMessage)
                            adapter.update(state.data)
                        }
                        is UIState.Error -> {
                            hideLoading(binding.loadingView)
                            showError(binding.errorMessage, state.error, binding.root)
                        }
                    }
                }
            }
        }
    }

    private fun establishRecyclerView() {
        adapter = CoinListRecyclerViewAdapter() {
            val action =
                CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(it.id)
            findNavController().navigate(action)
        }
        binding.coinList.adapter = adapter
        binding.coinList.layoutManager = LinearLayoutManager(this.context)
    }
}