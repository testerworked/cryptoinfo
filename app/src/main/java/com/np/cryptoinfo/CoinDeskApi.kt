package com.np.cryptoinfo

import retrofit2.Call
import retrofit2.http.GET

interface CoinDeskApi {
    @GET("v1/bpi/currentprice.json")
    fun getCurrentPrice(): Call<BitcoinPriceResponse>
}