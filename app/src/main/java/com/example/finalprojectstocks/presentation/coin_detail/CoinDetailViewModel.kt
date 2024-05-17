package com.example.finalprojectstocks.presentation.coin_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectstocks.data.network.dto.coinDetail.CoinDetailResponse
import com.example.finalprojectstocks.data.network.dto.coinDetail.CoinMarketPriceHistoryResponse
import com.example.finalprojectstocks.domain.models.CoinDetails
import com.example.finalprojectstocks.domain.repository.CoinRepository
import com.example.finalprojectstocks.domain.datastore.SharedPreferenceService
import com.example.finalprojectstocks.presentation.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import javax.inject.Inject


@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val repository: CoinRepository,
    private val sharedPreferenceService: SharedPreferenceService
) : ViewModel() {
    private val _coinDetails = MutableStateFlow<UIState<CoinDetails>>(UIState.Loading())
    public val coinDetails = _coinDetails

    fun getCoinDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _coinDetails.emit(UIState.Loading())
                val details = async { repository.getCoinDetailsById(id) }
                val marketHistory = async { repository.getCoinPriceHistoryById(id, sharedPreferenceService.getInt()) }
                _coinDetails.emit(UIState.Success(data = getCoinDetails(details.await(), marketHistory.await())))
            } catch (e: Exception) {
                _coinDetails.emit(UIState.Error(e.message ?: "Unknown error"))
            }
        }

    }

    private fun getCoinDetails(
        detailResponse: CoinDetailResponse,
        marketPriceHistoryResponse: CoinMarketPriceHistoryResponse
    ): CoinDetails {
        return CoinDetails(
            id = detailResponse.id,
            name = detailResponse.name,
            description = detailResponse.description.en,
            symbol = detailResponse.symbol,
            thumb = detailResponse.image.small,
            links = detailResponse.links.homepage,
            creationDate = detailResponse.genesis_date?.let { LocalDate.parse(it) },
            marketHistory = marketPriceHistoryResponse.prices.map {
                it[1]
            }
        )
    }
}