package com.rbppl.giphyapp

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class GifDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full)
val backButton = findViewById<ImageButton>(R.id.ic_back)
        val gifDetailImageView = findViewById<ImageView>(R.id.gifFullImageView)

        val gifUrl = intent.getStringExtra("gifUrl")

        Glide.with(this)
            .load(gifUrl)
            .into(gifDetailImageView)

        backButton.setOnClickListener {
            onBackPressed()
        }
    }

}
