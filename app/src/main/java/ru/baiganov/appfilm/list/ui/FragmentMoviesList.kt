package ru.baiganov.appfilm.list.ui

import android.content.res.Resources
import java.util.concurrent.TimeUnit
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import kotlinx.serialization.ExperimentalSerializationApi
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.api.ApiFactory
import ru.baiganov.appfilm.database.AppDatabase
import ru.baiganov.appfilm.databinding.FragmentMoviesListBinding
import ru.baiganov.appfilm.detail.ui.FragmentMoviesDetails
import ru.baiganov.appfilm.pojo.Movie
import ru.baiganov.appfilm.work.RefreshDataWorker


class FragmentMoviesList : Fragment() {

    private lateinit var adapter: MoviesAdapter
    private lateinit var viewModel: MoviesListViewModel
    private lateinit var recyclerMovies: RecyclerView
    private lateinit var binding: FragmentMoviesListBinding
    private val clickListener = ItemClickListener { movie -> doOnClick(movie) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TESTTT", "onViewCreated")
        initViews(view)
        setupViewModel()
    }

    @ExperimentalSerializationApi
    private fun setupViewModel() {
        val database: AppDatabase = AppDatabase.create(requireContext())
        viewModel = ViewModelProvider(this, MoviesListFactory(
                moviesDao = database.moviesDao(),
                apiService = ApiFactory.apiService,
        )).get(MoviesListViewModel::class.java)

        viewModel.movieList.observe(this, {
            updateAdapter(it)
        })

        viewModel.isNotifying.observe(viewLifecycleOwner) { notifying ->
            if (notifying) Toast.makeText(activity?.applicationContext, "No network", Toast.LENGTH_SHORT).show()
        }
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

    private fun setupWork() {

    }
}