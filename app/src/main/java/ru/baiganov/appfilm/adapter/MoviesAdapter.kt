package ru.baiganov.appfilm.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.data.Movie
import ru.baiganov.appfilm.data.loadMovies

class MoviesAdapter(
        private val listener: ItemClickListener
        ) : RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentItem = movies[position]
        holder.onBind(currentItem)
        holder.itemView.setOnClickListener {
            listener.onItemClick(movies[position])
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val poster: ImageView = itemView.findViewById(R.id.iv_poster_item)
    private val name: TextView = itemView.findViewById(R.id.tv_name_item)
    private val time: TextView = itemView.findViewById(R.id.tv_time)
    private val reviews: TextView = itemView.findViewById(R.id.tv_reviews_item)
    private val genres: TextView = itemView.findViewById(R.id.tv_tag_item)
    private val pg: TextView = itemView.findViewById(R.id.tv_pg_item)
    private val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)

    @SuppressLint("SetTextI18n")
    fun onBind(movie: Movie) {
        Glide.with(context)
            .load(movie.poster)
            .into(poster)

        name.text = movie.title
        time.text = movie.runtime.toString() + " " + context.getString(R.string.min)
        reviews.text = movie.numberOfRatings.toString() + " " + context.getString(R.string.reviews)
        pg.text = movie.minimumAge.toString() + context.getString(R.string.plus)
        var temp:String = ""
        movie.genres.forEach {
            temp = if (temp == "") {
                it.name
            } else {
                "$temp,${it.name}"
            }
        }
        genres.text = temp
        ratingBar.rating = movie.ratings / 2
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

fun interface ItemClickListener {
    fun onItemClick(movie: Movie)
}

