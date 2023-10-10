package com.rbppl.giphyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GifListFragment : Fragment() {
    private lateinit var adapter: GifAdapter
    private val apiKey = "Y6S3mTQ0LhEAs5NDHYsrsqGgt5PyH2uI"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gif_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val giphyApi = RetrofitClient.create()

        giphyApi.getTrendingGifs(apiKey, 26).enqueue(object : Callback<GiphyResponse> {
            override fun onResponse(call: Call<GiphyResponse>, response: Response<GiphyResponse>) {
                if (response.isSuccessful) {
                    val gifs = response.body()?.data ?: emptyList()
                    val gifUrls = gifs.map { it.images.fixed_height.url }

                    adapter = GifAdapter(requireContext(), gifUrls) { position ->
                        val selectedUrl = gifUrls[position]
                        val fragment = GifDetailFragment.newInstance(selectedUrl)
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .addToBackStack(null)
                            .commit()
                    }

                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<GiphyResponse>, t: Throwable) {
                // Обработка ошибки
            }
        })

        return view
    }
}
