package com.rbppl.giphyapp
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class GifDetailFragment : Fragment() {

    private var gifUrl: String? = null
    private lateinit var backButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gif_detail, container, false)
        backButton = view.findViewById(R.id.ic_back)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gifDetailImageView = view.findViewById<ImageView>(R.id.gifFullImageView)

        gifUrl = arguments?.getString(ARG_GIF_URL)

        gifUrl?.let {
            Glide.with(this)
                .load(it)
                .into(gifDetailImageView)
        }

        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    companion object {
        private const val ARG_GIF_URL = "gifUrl"

        fun newInstance(gifUrl: String): GifDetailFragment {
            val fragment = GifDetailFragment()
            val args = Bundle()
            args.putString(ARG_GIF_URL, gifUrl)
            fragment.arguments = args
            return fragment
        }
    }
}
