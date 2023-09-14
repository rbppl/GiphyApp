package com.rbppl.giphyapp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("gifs/trending")
    fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int
    ): Call<GiphyResponse>
}

data class GiphyResponse(val data: List<GifItem>)

data class GifItem(val images: GifImages)

data class GifImages(val fixed_height: GifImage)

data class GifImage(val url: String)
