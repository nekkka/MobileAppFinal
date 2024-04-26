package com.example.finalprojectstocks.model.network

import com.example.finalprojectstocks.model.entity.Stocks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StockService {
    @GET("stockprice?x-api-key=IQhgB1xircwnSIg6p2lVKQ==k3DIFrjL15mkXojU")
    fun fetchKotikList(@Query("name")name: String): Call<List<Stocks>>
}