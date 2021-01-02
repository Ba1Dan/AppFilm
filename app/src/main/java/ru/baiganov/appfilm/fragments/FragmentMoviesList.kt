package ru.baiganov.appfilm.fragments

import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.adapter.ItemClickListener
import ru.baiganov.appfilm.adapter.MoviesAdapter
import ru.baiganov.appfilm.data.Movie
import ru.baiganov.appfilm.databinding.FragmentMoviesListBinding
import ru.baiganov.appfilm.screens.AssetMovieRepo
import ru.baiganov.appfilm.screens.movies.MoviesListFactory
import ru.baiganov.appfilm.screens.movies.MoviesListViewModel


class FragmentMoviesList : Fragment() {

    private lateinit var adapter: MoviesAdapter
    private lateinit var viewModel: MoviesListViewModel
    private lateinit var recyclerMovies: RecyclerView
    private lateinit var binding: FragmentMoviesListBinding
    private val clickListener = ItemClickListener { movie -> doOnClick(movie) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        viewModel = ViewModelProvider(this, MoviesListFactory(AssetMovieRepo(requireContext()))).get(
                MoviesListViewModel::class.java
        )
        viewModel.movieList.observe(this, Observer {
            updateAdapter(it)
        })
    }

    private fun initViews(view: View) {
        binding = FragmentMoviesListBinding.bind(view)
        recyclerMovies = binding.rvMovies
        adapter = MoviesAdapter(clickListener)
        recyclerMovies.layoutManager = GridLayoutManager(requireContext(), getColumns())
        recyclerMovies.adapter = adapter
    }

    private fun getColumns(): Int {
        val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val scalingFactor = 180
        return (dpWidth / scalingFactor).toInt()
    }

    private fun updateAdapter(movies: List<Movie>) {
        adapter.bindMovies(movies)
    }

    private fun doOnClick(movie: Movie) {
        fragmentManager?.beginTransaction()?.apply {
            add(R.id.fl_main_activity, FragmentMoviesDetails.getNewInstance(movie))
            addToBackStack(null)
            commit()
        }
    }
}