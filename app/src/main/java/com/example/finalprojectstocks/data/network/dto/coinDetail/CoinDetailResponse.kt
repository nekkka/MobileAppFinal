package com.example.finalprojectstocks.data.network.dto.coinDetail


data class CoinImage(
    val small: String
)

data class CoinDescription(
    val en: String
)

data class CoinLinks(
    val homepage: List<String>
)

data class CoinDetailResponse(
    val id: String,
    val coin_id: Int,
    val name: String,
    val symbol: String,
    val image: CoinImage,
    val genesis_date: String?,
    val description:CoinDescription,
    val links: CoinLinks
)
