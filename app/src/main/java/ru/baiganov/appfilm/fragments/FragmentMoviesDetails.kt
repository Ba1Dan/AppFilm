package ru.baiganov.appfilm.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.adapter.ActorsAdapter
import ru.baiganov.appfilm.data.Actor
import ru.baiganov.appfilm.data.Movie

class FragmentMoviesDetails : Fragment() {

    private lateinit var adapter: ActorsAdapter
    private var recyclerActors: RecyclerView? = null
    private lateinit var actors: List<Actor>

    @SuppressLint("SetTextI18n")
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
            val ratingBar: RatingBar = view.findViewById(R.id.rb_stars)
            val cast: TextView = view.findViewById(R.id.tv_cast)

            tvTitle.text = movie.title
            tvMinAge.text = movie.minimumAge.toString() + requireContext().getString(R.string.plus)
            tvReviews.text = movie.numberOfRatings.toString() + " " + requireContext().getString(R.string.reviews)
            tvStoryline.text = movie.overview
            ratingBar.rating = movie.ratings / 2
            var temp:String = ""
            movie.genres.forEach {
                temp = if (temp == "") {
                    it.name
                } else {
                    "$temp,${it.name}"
                }
            }
            tvGenres.text = temp
            actors = movie.actors
            if (movie.actors.isEmpty()) {
                cast.isVisible = false
            }
            Glide.with(requireContext())
                    .load(movie.backdrop)
                    .into(ivBackDrop)
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