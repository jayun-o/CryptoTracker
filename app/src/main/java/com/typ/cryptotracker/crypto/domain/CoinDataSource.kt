package com.typ.cryptotracker.crypto.domain

import com.typ.cryptotracker.core.domain.util.NetworkError
import com.typ.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}