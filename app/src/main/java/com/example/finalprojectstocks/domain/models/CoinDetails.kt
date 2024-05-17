package com.example.finalprojectstocks.domain.models

import kotlinx.datetime.LocalDate


data  class CoinDetails(
    val id: String,
    val name: String,
    val symbol: String,
    val thumb: String,
    val description: String,
    val links: List<String>,
    val creationDate: LocalDate?,
    val marketHistory: List<Double>
)