package com.example.finalprojectstocks.presentation.coin_detail

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.finalprojectstocks.databinding.FragmentCoinDetailBinding
import com.example.finalprojectstocks.domain.models.CoinDetails
import com.example.finalprojectstocks.presentation.UIState
import com.example.finalprojectstocks.presentation.UIStateHandler
import com.example.finalprojectstocks.presentation.hideError
import com.example.finalprojectstocks.presentation.hideLoading
import com.example.finalprojectstocks.presentation.showError
import com.example.finalprojectstocks.presentation.showLoading
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CoinDetailFragment : Fragment(), UIStateHandler {
    private val viewModel: CoinDetailViewModel by viewModels()
    private var _binding: FragmentCoinDetailBinding? = null
    private val binding get() = _binding!!

    private val args: CoinDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root.apply {
            initData()
            observeData()
        }
    }

    private fun initData() {
        viewModel.getCoinDetails(args.coinId)
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.coinDetails.collectLatest { state ->
                when (state) {
                    is UIState.Success -> {
                        hideLoading(binding.loadingView)
                        hideError(binding.errorMessage)
                        bindData(state.data)
                    }
                    is UIState.Loading -> showLoading(binding.loadingView)
                    is UIState.Error -> {
                        hideLoading(binding.loadingView)
                        showError(binding.errorMessage, state.error, binding.root)
                    }
                }
            }
        }
    }

    private fun populateTheChart(marketHistory: List<Double>) {
        val entries = marketHistory.mapIndexed { index, price ->
            Entry(index.toFloat(), price.toFloat())
        }
        binding.chart.apply {
            data = LineData(LineDataSet(entries, "Price").apply {
                valueTextSize = 14f
            })
            invalidate()
            setScaleEnabled(false)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindData(coinDetails: CoinDetails) {
        with(binding) {
            coinDetailName.text = coinDetails.name
            coinDetailSymbol.text = coinDetails.symbol
            coinSearchingID.text = "For search: ${coinDetails.id}"
            coinDetailDescr.apply {
                text = coinDetails.description
                movementMethod = ScrollingMovementMethod()
            }
            coinDetailCreationDate.text = coinDetails.creationDate?.toString() ?: "N/A"
            coinDetailImage.load(coinDetails.thumb)
            populateTheChart(coinDetails.marketHistory)
            populateLinks(coinDetails.links)
        }
    }



    @SuppressLint("SetTextI18n")
    private fun populateLinks(links: List<String>) {
        val firstHomePageLink = links.firstOrNull()
        firstHomePageLink?.let {
            binding.links.text = "Homepage: ${it}"
            binding.links.movementMethod = LinkMovementMethod()
        }
    }

}