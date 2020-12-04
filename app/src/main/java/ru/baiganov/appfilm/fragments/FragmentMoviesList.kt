package ru.baiganov.appfilm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.baiganov.appfilm.adapter.MoviesAdapter
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.domain.MoviesDataSource

class FragmentMoviesList : Fragment(){

    private lateinit var adapter: MoviesAdapter
    private var recyclerMovies:RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerMovies = view.findViewById(R.id.rv_movies)
        adapter = MoviesAdapter()
        recyclerMovies?.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerMovies?.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    override fun onDetach() {
        recyclerMovies = null
        super.onDetach()
    }

    private fun updateData() {
        (recyclerMovies?.adapter as? MoviesAdapter)?.apply {
            bindActors(MoviesDataSource().getMovies())
        }
    }
}