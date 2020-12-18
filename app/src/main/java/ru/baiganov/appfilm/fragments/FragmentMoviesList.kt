package ru.baiganov.appfilm.fragments

import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.adapter.ItemClickListener
import ru.baiganov.appfilm.adapter.MoviesAdapter
import ru.baiganov.appfilm.data.Movie
import ru.baiganov.appfilm.data.loadMovies


class FragmentMoviesList : Fragment() {

    private lateinit var adapter: MoviesAdapter
    private var recyclerMovies: RecyclerView? = null
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerMovies = view.findViewById(R.id.rv_movies)
        adapter = MoviesAdapter(clickListener)
        recyclerMovies?.layoutManager = GridLayoutManager(requireContext(), getColumns())
        recyclerMovies?.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    private fun getColumns(): Int {
        val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val scalingFactor = 180
        return (dpWidth / scalingFactor).toInt()
    }

    private fun updateData() {
        scope.launch {
            val data = loadMovies(requireContext())
            withContext(Dispatchers.Main) {
                adapter.bindMovies(data)
            }
        }
    }

    private val clickListener = ItemClickListener { movie -> doOnClick(movie) }

    private fun doOnClick(movie: Movie) {
        fragmentManager?.beginTransaction()?.apply {
            add(R.id.fl_main_activity, FragmentMoviesDetails.getNewInstance(movie))
            addToBackStack(null)
            commit()
        }
    }
}