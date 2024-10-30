package com.typ.cryptotracker.crypto.data.networking

import com.typ.cryptotracker.core.data.networking.constructUrl
import com.typ.cryptotracker.core.data.networking.safeCall
import com.typ.cryptotracker.core.domain.util.NetworkError
import com.typ.cryptotracker.core.domain.util.Result
import com.typ.cryptotracker.core.domain.util.map
import com.typ.cryptotracker.crypto.data.mappers.toCoin
import com.typ.cryptotracker.crypto.data.mappers.toCoinPrice
import com.typ.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.typ.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.typ.cryptotracker.crypto.domain.Coin
import com.typ.cryptotracker.crypto.domain.CoinDataSource
import com.typ.cryptotracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}