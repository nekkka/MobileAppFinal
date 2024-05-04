package com.example.finalprojectstocks.model.network

import com.example.finalprojectstocks.model.entity.Stocks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StockService {
    @GET("stockprice")
    fun fetchStocksList(@Query("ticker")ticker: String): Call<Stocks>
}