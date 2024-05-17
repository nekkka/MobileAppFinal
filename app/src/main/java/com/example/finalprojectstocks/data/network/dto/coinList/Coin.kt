package com.example.mobileappfinal.data.network.dto.coinList

import com.example.mobileappfinal.domain.models.Coin


data class CoinResponse(
    val item: CoinDTO
)

data class CoinDTO(
    val id: String,
    val coin_id: Int,
    val name: String,
    val symbol: String,
    val thumb: String
)


fun CoinDTO.toCoin(): Coin {
    return Coin(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        thumb = this.thumb
    )
}