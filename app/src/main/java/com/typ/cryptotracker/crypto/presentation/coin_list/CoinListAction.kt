package com.typ.cryptotracker.crypto.presentation.coin_list

import com.typ.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListAction
}