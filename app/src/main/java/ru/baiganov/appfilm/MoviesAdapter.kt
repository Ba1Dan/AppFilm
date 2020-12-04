package ru.baiganov.appfilm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.baiganov.appfilm.data.Actor
import ru.baiganov.appfilm.data.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var movies = listOf<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        var currentItem = movies[position]
        holder.onBind(currentItem)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //private val poster: ImageView? = itemView.findViewById(R.id.iv_poster_item)
        private val name: TextView? = itemView.findViewById(R.id.tv_name_item)
        private val time: TextView? = itemView.findViewById(R.id.tv_time)
        private val reviews: TextView? = itemView.findViewById(R.id.tv_reviews_item)
        private val tag: TextView? = itemView.findViewById(R.id.tv_tag_item)
        private val pg: TextView? = itemView.findViewById(R.id.tv_pg_item)

        fun onBind(movie: Movie) {
            name?.text = movie.name
            time?.text = movie.time
            reviews?.text = movie.reviews
            tag?.text = movie.tag
            pg?.text = movie.pg
        }
    }
}

