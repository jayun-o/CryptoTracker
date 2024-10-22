package com.typ.cryptotracker.crypto.domain

import java.time.LocalDateTime

data class Coin (
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24hr: Double,
)