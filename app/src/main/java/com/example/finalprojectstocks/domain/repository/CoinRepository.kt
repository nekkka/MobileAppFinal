package com.example.finalprojectstocks.domain.repository

import com.example.finalprojectstocks.data.network.dto.coinDetail.CoinDetailResponse
import com.example.finalprojectstocks.data.network.dto.coinDetail.CoinMarketPriceHistoryResponse
import com.example.finalprojectstocks.data.network.dto.coinList.CoinDTO

interface CoinRepository {
    suspend fun getAllCoins(): List<CoinDTO>
    suspend fun getCoinDetailsById(coinId: String): CoinDetailResponse
    suspend fun getCoinPriceHistoryById(coinId: String, days: Int): CoinMarketPriceHistoryResponse
}