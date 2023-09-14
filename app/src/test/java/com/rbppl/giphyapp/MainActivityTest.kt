package com.rbppl.giphyapp
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityTest {

    private lateinit var mainActivity: MainActivity

    @Mock
    private lateinit var giphyApi: GiphyApi

    @Mock
    private lateinit var call: Call<GiphyResponse>

    @Before
    fun setup() {
        mainActivity = MainActivity()
        mainActivity.apiKey = "test_api_key"
    }

    @Test
    fun testApiResponseSuccess() {
        val response = GiphyResponse(listOf(/* ваш список GIF-ов для успешного ответа */))

        `when`(giphyApi.getTrendingGifs("test_api_key", 26)).thenReturn(call)
        `when`(call.enqueue(any())).thenAnswer {
            val callback = it.getArgument<Callback<GiphyResponse>>(0)
            callback.onResponse(call, Response.success(response))
        }

    }

    @Test
    fun testApiResponseFailure() {
        `when`(giphyApi.getTrendingGifs("test_api_key", 26)).thenReturn(call)
        `when`(call.enqueue(any())).thenAnswer {
            val callback = it.getArgument<Callback<GiphyResponse>>(0)
            callback.onFailure(call, mock(Throwable::class.java))
        }

    }
}