package com.example.finalprojectstocks.model.entity

import com.google.gson.annotations.SerializedName

data class Stocks (
    val ticker: String,
    val name: String,
    val price: Int,
    val exchange: String,
    val updated: Int
)