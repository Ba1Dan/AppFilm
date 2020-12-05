package ru.baiganov.appfilm.adapter

import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.baiganov.appfilm.R
import ru.baiganov.appfilm.data.Movie
import java.io.File

class MoviesAdapter(
        private val listener: OnItemClicked
): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
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

    fun bindActors(newMovies: List<Movie>) {
        movies = newMovies
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.iv_poster_item)
        private val name: TextView = itemView.findViewById(R.id.tv_name_item)
        private val time: TextView = itemView.findViewById(R.id.tv_time)
        private val reviews: TextView = itemView.findViewById(R.id.tv_reviews_item)
        private val tag: TextView = itemView.findViewById(R.id.tv_tag_item)
        private val pg: TextView = itemView.findViewById(R.id.tv_pg_item)
        private val favourite:ImageView = itemView.findViewById(R.id.iv_favourite_item)

        fun onBind(movie: Movie) {
            Glide.with(context)
                    .load(movie.poster)
                    .into(poster)

            name.text = movie.name
            time.text = movie.time
            reviews.text = movie.reviews
            tag.text = movie.tag
            pg.text = movie.pg
            if (movie.favourite) {
                favourite.setImageResource(R.drawable.ic_like)
            } else {
                favourite.setImageResource(R.drawable.ic_no_like)
            }
        }
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface OnItemClicked {
    fun onItemClick(movie: Movie)
}

