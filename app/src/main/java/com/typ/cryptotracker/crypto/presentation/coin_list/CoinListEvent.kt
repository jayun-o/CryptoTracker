package com.typ.cryptotracker.crypto.presentation.coin_list

import com.typ.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}