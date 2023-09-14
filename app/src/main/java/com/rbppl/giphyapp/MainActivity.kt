package com.rbppl.giphyapp

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var apiKey = "Y6S3mTQ0LhEAs5NDHYsrsqGgt5PyH2uI"
    private lateinit var adapter: GifAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gridView = findViewById<GridView>(R.id.gridView)
        val giphyApi = RetrofitClient.create()

        giphyApi.getTrendingGifs(apiKey, 26).enqueue(object : Callback<GiphyResponse> {
            override fun onResponse(call: Call<GiphyResponse>, response: Response<GiphyResponse>) {
                if (response.isSuccessful) {
                    val gifs = response.body()?.data ?: emptyList()
                    val gifUrls = gifs.map { it.images.fixed_height.url }

                    adapter = GifAdapter(this@MainActivity, gifUrls)
                    gridView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<GiphyResponse>, t: Throwable) {
                // Обработка ошибки
            }
        })

        gridView.setOnItemClickListener { _, _, position, _ ->
            val selectedUrl = adapter.getItem(position) // Получите URL гифки
            val intent = Intent(this, GifDetailActivity::class.java)
            intent.putGifUrlExtra("gifUrl", selectedUrl as String) // Передайте URL гифки
            startActivity(intent)
        }

}

private fun Intent.putGifUrlExtra(key: String, gifUrl: String) {
    putExtra(key, gifUrl)
}}
