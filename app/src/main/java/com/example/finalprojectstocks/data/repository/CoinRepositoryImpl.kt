package com.example.finalprojectstocks.data.repository

import com.example.finalprojectstocks.data.network.CoinGeckoAPI
import com.example.finalprojectstocks.data.network.dto.coinDetail.CoinDetailResponse
import com.example.finalprojectstocks.data.network.dto.coinDetail.CoinMarketPriceHistoryResponse
import com.example.finalprojectstocks.data.network.dto.coinList.CoinDTO
import com.example.finalprojectstocks.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinAPI: CoinGeckoAPI
): CoinRepository {
    override suspend fun getAllCoins(): List<CoinDTO> {
        val coins = coinAPI.getTrendingCoins().coins.map {
            it.item
        }
        return coins
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetailResponse {
        return coinAPI.getCoinDetails(coinId)
    }

    override suspend fun getCoinPriceHistoryById(coinId: String, days: Int): CoinMarketPriceHistoryResponse {
        return coinAPI.getCoinHistoryPrice(coinId, days)
    }
}