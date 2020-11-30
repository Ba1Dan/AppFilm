package ru.baiganov.appfilm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesDetails : Fragment() {

    private var tvBackToMovieList:TextView? = null
    private var listener:ClickListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvBackToMovieList = view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener{ listener?.backToMovieList() }
        }
    }

    fun setListener(l: ClickListener) {
        listener = l
    }

    interface ClickListener {
        fun backToMovieList()
    }
}