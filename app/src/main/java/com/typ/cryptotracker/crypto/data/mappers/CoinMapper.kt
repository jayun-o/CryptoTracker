package com.typ.cryptotracker.crypto.data.mappers

import com.typ.cryptotracker.crypto.data.networking.dto.CoinDto
import com.typ.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24hr = changePercent24Hr
    )
}