package com.rbppl.giphyapp
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
class GifAdapter(private val context: Context, private val gifUrls: List<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return gifUrls.size
    }

    override fun getItem(position: Int): Any {
        return gifUrls[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val gifUrl = gifUrls[position]
        val imageView = ImageView(context)
        Glide.with(context).load(gifUrl).into(imageView)
        return imageView
    }
}
