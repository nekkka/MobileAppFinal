package com.example.mobileappfinal.data.network

import com.example.mobileappfinal.data.network.dto.coinDetail.CoinDetailResponse
import com.example.mobileappfinal.data.network.dto.coinDetail.CoinMarketPriceHistoryResponse
import com.example.mobileappfinal.data.network.dto.coinList.TrendingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoAPI {


    @GET("search/trending")
    suspend fun getTrendingCoins(): TrendingResponse

    @GET("coins/{id}?localization=false&tickers=false&market_data=false&community_data=false&developer_data=false&sparkline=false")
    suspend fun getCoinDetails(@Path("id") coinId: String): CoinDetailResponse

    @GET("coins/{id}/market_chart?vs_currency=usd&interval=daily")
    suspend fun getCoinHistoryPrice(@Path("id") coinId: String, @Query("days") days: Int): CoinMarketPriceHistoryResponse
}
