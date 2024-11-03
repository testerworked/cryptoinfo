package com.np.cryptoinfo

data class BitcoinPriceResponse(
    val bpi: Bpi,
    val chartName: String,
    val disclaimer: String,
    val time: Time
)

data class Bpi(
    val EUR: Rate,
    val GBP: Rate,
    val USD: Rate
)

data class Rate(
    val code: String,
    val rate: String,
    val rate_float: Double,
    val symbol: String
)

data class Time(
    val updated: String,
    val updatedISO: String,
    val updateduk: String
)