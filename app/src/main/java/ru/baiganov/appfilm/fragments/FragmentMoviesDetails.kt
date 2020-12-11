package ru.baiganov.appfilm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.homework.features.data.Actor
import com.android.academy.fundamentals.homework.features.data.Movie
import com.bumptech.glide.Glide
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.adapter.ActorsAdapter
import ru.baiganov.appfilm.adapter.MoviesAdapter

class FragmentMoviesDetails : Fragment() {

    private lateinit var adapter: ActorsAdapter
    private var recyclerActors: RecyclerView? = null
    private lateinit var actors: List<Actor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val movie: Movie? = arguments?.getParcelable<Movie>("movie")
        val view: View = inflater.inflate(R.layout.fragment_movies_details, container, false)
        if (movie != null) {
            val tvTitle:TextView = view.findViewById(R.id.tv_name)
            val tvGenres: TextView = view.findViewById(R.id.tv_tag)
            val tvMinAge: TextView = view.findViewById(R.id.tv_pg)
            val tvStoryline: TextView = view.findViewById(R.id.tv_storyline)
            val tvReviews: TextView = view.findViewById(R.id.tv_reviews)
            val ivBackDrop: ImageView = view.findViewById(R.id.iv_poster)
            Glide.with(requireContext())
                .load(movie.backdrop)
                .into(ivBackDrop)
            tvTitle.text = movie.title
            tvMinAge.text = movie.minimumAge.toString()
            tvReviews.text = movie.ratings.toString()
            tvStoryline.text = movie.overview
            actors = movie.actors
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_back).apply {
            setOnClickListener{
                fragmentManager?.popBackStack()
            }
        }

        recyclerActors = view.findViewById(R.id.rv_actors)
        adapter = ActorsAdapter()
        recyclerActors?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerActors?.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        adapter.apply {
            onBindActors(actors)
        }
    }

    companion object {
        fun getNewInstance(movie: Movie):FragmentMoviesDetails {
            val bundle = Bundle()
            bundle.putParcelable("movie", movie)
            val fragment = FragmentMoviesDetails()
            fragment.arguments = bundle
            return fragment
        }
    }
}