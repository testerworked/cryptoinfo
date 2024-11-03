package com.np.cryptoinfo

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var priceTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        priceTextView = findViewById(R.id.price_text_view)

        fetchBitcoinPrice()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun fetchBitcoinPrice() {
        val call = ApiClient.api.getCurrentPrice()
        call.enqueue(object : Callback<BitcoinPriceResponse> {
            override fun onResponse(call: Call<BitcoinPriceResponse>, response: Response<BitcoinPriceResponse>) {
                if (response.isSuccessful) {
                    val bitcoinPrice = response.body()
                    if (bitcoinPrice != null) {
                        displayPrice(bitcoinPrice)
                    }
                } else {
                    priceTextView.text = "Failed to retrieve data"
                }
            }

            override fun onFailure(call: Call<BitcoinPriceResponse>, t: Throwable) {
                priceTextView.text = "Error: ${t.message}"
            }
        })
    }
    private fun displayPrice(bitcoinPrice: BitcoinPriceResponse) {
        val priceText = """
            Цена Bitcoin:
            USD: ${bitcoinPrice.bpi.USD.rate}
            EUR: ${bitcoinPrice.bpi.EUR.rate}
            GBP: ${bitcoinPrice.bpi.GBP.rate}
            
            Эти данные были получены на основе индекса цен на биткойны CoinDesk (USD). Данные о валютах, отличных от USD, пересчитаны с использованием почасового коэффициента пересчета из openexchangerates.org
        """.trimIndent()
        priceTextView.text = priceText
    }


}