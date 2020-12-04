package ru.baiganov.appfilm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.baiganov.appfilm.R

class FragmentMoviesDetails : Fragment() {

    private var tvBackToMovieList:TextView? = null
    private var listener: ClickListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvBackToMovieList = view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener{
                //listener?.backToMovieList()
                fragmentManager?.popBackStack()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickListener){
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface ClickListener {
        fun backToMovieList()
    }
}