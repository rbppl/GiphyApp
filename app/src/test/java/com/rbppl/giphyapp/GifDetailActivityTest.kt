package com.rbppl.giphyapp
import android.view.View
import android.widget.ImageButton
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GifDetailActivityTest {

    private lateinit var gifDetailActivity: GifDetailActivity

    @Before
    fun setup() {
        gifDetailActivity = GifDetailActivity()
    }

    @Test
    fun testLoadGifImage() {
}

    @Test
    fun testBackButtonClicked() {
        val backButton = mock(ImageButton::class.java)
        `when`(backButton.setOnClickListener(any())).thenAnswer {
            val listener = it.getArgument<View.OnClickListener>(0)
            listener.onClick(backButton)
        }

    }
}