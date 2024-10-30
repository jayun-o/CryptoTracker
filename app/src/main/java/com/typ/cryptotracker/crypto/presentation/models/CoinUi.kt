package com.typ.cryptotracker.crypto.presentation.models

import androidx.annotation.DrawableRes
import com.typ.cryptotracker.crypto.domain.Coin
import com.typ.cryptotracker.core.presentation.util.getDrawableIdForCoin
import com.typ.cryptotracker.crypto.presentation.coin_detail.DataPoint
import java.text.NumberFormat
import java.util.Locale

data class CoinUi (
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int,
    val coinPriceHistory: List<DataPoint> = emptyList()

)

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Coin.toCoinUi(): CoinUi{
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        priceUsd = priceUsd.toDisplayableNumber(),
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}